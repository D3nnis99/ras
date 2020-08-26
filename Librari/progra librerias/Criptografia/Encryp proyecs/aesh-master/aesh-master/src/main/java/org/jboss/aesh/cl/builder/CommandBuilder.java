/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aesh.cl.builder;

import org.jboss.aesh.cl.parser.CommandLineParserException;
import org.jboss.aesh.cl.parser.OptionParserException;
import org.jboss.aesh.cl.internal.ProcessedCommand;
import org.jboss.aesh.cl.internal.ProcessedCommandBuilder;
import org.jboss.aesh.cl.internal.ProcessedOption;
import org.jboss.aesh.cl.internal.ProcessedOptionBuilder;
import org.jboss.aesh.cl.parser.AeshCommandLineParser;
import org.jboss.aesh.cl.populator.CommandPopulator;
import org.jboss.aesh.cl.result.ResultHandler;
import org.jboss.aesh.cl.validator.CommandValidator;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.container.AeshCommandContainer;
import org.jboss.aesh.console.command.container.CommandContainer;
import org.jboss.aesh.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder to create commands during runtime
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 * @author <a href="mailto:danielsoro@gmail.com">Daniel Cunha (soro)</a>
 */
public class CommandBuilder {

    private String name;
    private String description;
    private Command command;
    private CommandValidator<?> validator;
    private ResultHandler resultHandler;
    private ProcessedOption argument;
    private List<ProcessedOption> options;
    private List<CommandBuilder> children;
    private CommandLineParserException parserException;
    private CommandPopulator<?, ? extends Command> populator;

    public CommandBuilder() {
    }

    public CommandBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CommandBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder command(Command command) {
        this.command = command;
        return this;
    }

    public CommandBuilder command(Class<? extends Command> command) {
        this.command = ReflectionUtil.newInstance(command);
        return this;
    }

    public CommandBuilder validator(CommandValidator<?> commandValidator) {
        this.validator = commandValidator;
        return this;
    }

    public CommandBuilder validator(Class<? extends CommandValidator> commandValidator) {
        this.validator = ReflectionUtil.newInstance(commandValidator);
        return this;
    }

    public CommandBuilder populator(CommandPopulator<?, ? extends Command> populator) {
        this.populator = populator;
        return this;
    }

    public CommandBuilder resultHandler(ResultHandler resultHandler) {
        this.resultHandler = resultHandler;
        return this;
    }

    public CommandBuilder resultHandler(Class<? extends ResultHandler> resultHandler) {
        this.resultHandler = ReflectionUtil.newInstance(resultHandler);
        return this;
    }

    public CommandBuilder argument(ProcessedOption argument) {
        this.argument = argument;
        return this;
    }

    public CommandBuilder addOption(ProcessedOption option) {
        if(options == null)
            options = new ArrayList<>();
        options.add(option);
        return this;
    }

    public CommandBuilder addOption(ProcessedOptionBuilder option) {
        if(options == null)
            options = new ArrayList<>();
        try {
            options.add(option.create());
        }
        catch (OptionParserException ope) {
            parserException = ope;
        }
        return this;
    }

    public CommandBuilder addOptions(List<ProcessedOption> options) {
        if(this.options == null)
            this.options = new ArrayList<>();
        this.options.addAll(options);
        return this;
    }

    public CommandBuilder addChild(CommandBuilder child) {
        if(children == null)
            children = new ArrayList<>();
        this.children.add(child);
        return this;
    }

    public CommandBuilder addChildren(List<CommandBuilder> children) {
        if(this.children == null)
            this.children = new ArrayList<>();
        this.children.addAll(children);
        return this;
    }

    public CommandContainer create() {
        try {
            if(parserException != null) {
                return new AeshCommandContainer<>(parserException.getMessage());
            }
            return new AeshCommandContainer<>(createParser());
        }
        catch (CommandLineParserException e) {
            return new AeshCommandContainer<>(e.getMessage());
        }
    }

    private AeshCommandLineParser createParser() throws CommandLineParserException {
        if(command == null)
            throw new CommandLineParserException("Command object is null, cannot create command");
        ProcessedCommand processedCommand = createProcessedCommand();
        AeshCommandLineParser parser = new AeshCommandLineParser<>(processedCommand);
        if(children != null) {
            for(CommandBuilder builder : children) {
                parser.addChildParser(builder.createParser());
            }
        }
        return parser;
    }

    private ProcessedCommand createProcessedCommand() throws CommandLineParserException {
        return new ProcessedCommandBuilder()
                .name(name)
                .command(command)
                .description(description)
                .addOptions(options)
                .resultHandler(resultHandler)
                .validator(validator)
                .argument(argument)
                .populator(populator)
                .create();
    }
}
