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
package org.jboss.aesh.cl.parser;

import org.jboss.aesh.cl.CommandLine;
import org.jboss.aesh.cl.internal.OptionType;
import org.jboss.aesh.cl.internal.ProcessedCommand;
import org.jboss.aesh.cl.internal.ProcessedOption;
import org.jboss.aesh.cl.populator.CommandPopulator;
import org.jboss.aesh.console.Config;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.parser.AeshLine;
import org.jboss.aesh.parser.Parser;
import org.jboss.aesh.parser.ParserStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple command line parser.
 * It parses a given string based on the Command given and
 * returns a {@link org.jboss.aesh.cl.CommandLine}
 *
 * It can also print a formatted usage/help information.
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class AeshCommandLineParser<C extends Command> implements CommandLineParser<C> {

    private final ProcessedCommand<C> processedCommand;
    private static final String EQUALS = "=";
    private List<CommandLineParser<? extends Command>> childParsers;
    private boolean isChild = false;

    public AeshCommandLineParser(ProcessedCommand<C> processedCommand) {
        this.processedCommand = processedCommand;
    }

    @Override
    public void addChildParser(CommandLineParser<? extends Command> commandLineParser) {
        if(childParsers == null)
            childParsers = new ArrayList<>();
        commandLineParser.setChild(true);
        childParsers.add(commandLineParser);
    }

    public List<CommandLineParser<? extends Command>> getChildParsers() {
        return childParsers;
    }

    @Override
    public void setChild(boolean child) {
        isChild = child;
    }

    @Override
    public List<String> getAllNames() {
        if (isGroupCommand()) {
            List<CommandLineParser<? extends Command>> parsers = getChildParsers();
            List<String> names = new ArrayList<>(parsers.size());
            for (CommandLineParser child : parsers) {
                names.add(processedCommand.getName()+" "+child.getProcessedCommand().getName());
            }
            return names;
        }
        else {
            List<String> names = new ArrayList<>(1);
            names.add(processedCommand.getName());
            return names;
        }
    }

    public boolean isChild() {
        return isChild;
    }

    @Override
    public CommandLineParser<? extends Command> getChildParser(String name) {
        if(!isGroupCommand())
            return null;
        for (CommandLineParser clp : getChildParsers()) {
            if(clp.getProcessedCommand().getName().equals(name))
                return clp;
        }
        return null;
    }

    @Override
    public List<CommandLineParser<? extends Command>> getAllChildParsers() {
        if(isGroupCommand())
            return getChildParsers();
        else
           return new ArrayList<>();
    }

    @Override
    public ProcessedCommand<C> getProcessedCommand() {
        return processedCommand;
    }

    @Override
    public C getCommand() {
        return processedCommand.getCommand();
    }

    @Override
    public CommandLineCompletionParser getCompletionParser() {
        return new AeshCommandLineCompletionParser(this);
    }

    @Override
    public CommandPopulator getCommandPopulator() {
        return processedCommand.getCommandPopulator();
    }

    /**
     * Returns a usage String based on the defined command and options.
     * Useful when printing "help" info etc.
     */
    @Override
    public String printHelp() {
        List<CommandLineParser<? extends Command>> parsers = getChildParsers();
        if (parsers != null && parsers.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(processedCommand.printHelp())
                    .append(Config.getLineSeparator())
                    .append(processedCommand.getName())
                    .append(" commands:")
                    .append(Config.getLineSeparator());
            for (CommandLineParser child : parsers)
                sb.append("    ").append(child.getProcessedCommand().getName()).append(Config.getLineSeparator());

            return sb.toString();
        }
        else
            return processedCommand.printHelp();
    }

    /**
     * Parse a command line with the defined command as base of the rules.
     * If any options are found, but not defined in the command object an
     * CommandLineParserException will be thrown.
     * Also, if a required option is not found or options specified with value,
     * but is not given any value an OptionParserException will be thrown.
     *
     * The options found will be returned as a {@link org.jboss.aesh.cl.CommandLine} object where
     * they can be queried after.
     *
     * @param line input
     * @return CommandLine
     */
    @Override
    public CommandLine<? extends Command> parse(String line) {
        return parse(line, false);
    }

    @Override
    public CommandLine<? extends Command> parse(AeshLine line, boolean ignoreRequirements) {
        if(line.getWords().size() > 0) {
            if(processedCommand.getName().equals(line.getWords().get(0))) {
                if(isGroupCommand() && line.getWords().size() > 1) {
                   CommandLineParser<? extends Command> clp = getChildParser(line.getWords().get(1));
                    if(clp == null)
                        return parse(line.getWords(), ignoreRequirements);
                    //we have a group command
                    else
                        return clp.parse(line.getWords(), ignoreRequirements);
                }
                else
                    return parse(line.getWords(), ignoreRequirements);
            }
        }
        else if(line.getStatus() != ParserStatus.OK)
            return new CommandLine<>(new CommandLineParserException(line.getErrorMessage()));

        return new CommandLine<>(new CommandLineParserException("Command:"+ processedCommand +", not found in: "+line));
    }
    /**
     * Parse a command line with the defined command as base of the rules.
     * If any options are found, but not defined in the command object an
     * CommandLineParserException will be thrown.
     * Also, if a required option is not found or options specified with value,
     * but is not given any value an CommandLineParserException will be thrown.
     *
     * The options found will be returned as a {@link CommandLine} object where
     * they can be queried after.
     *
     * @param line input
     * @param ignoreRequirements if we should ignore
     * @return CommandLine
     */
    @Override
    public CommandLine<? extends Command> parse(String line, boolean ignoreRequirements) {
        return parse(Parser.findAllWords(line), ignoreRequirements);
    }

    /**
     * Parse a command line with the defined command as base of the rules.
     * This method is useful when parsing a command line program thats not
     * in aesh, but rather a standalone command that want to parse input
     * parameters.
     *
     * @param lines input
     * @param ignoreRequirements if we should ignore
     * @return CommandLine
     */
    @Override
    public CommandLine<? extends Command> parse(List<String> lines, boolean ignoreRequirements) {
        clear();
        CommandLine<? extends Command> commandLine = new CommandLine<>(this);
        if(processedCommand.hasArgument())
            commandLine.setArgument(processedCommand.getArgument());
        ProcessedOption active = null;
        boolean addedArgument = false;
        int startWord = 1;
        if(isChild)
            startWord = 2;
        //skip first entry since that's the name of the command
        for(int i=startWord; i < lines.size(); i++) {
            String parseLine = lines.get(i);
            ProcessedOption currentOption = null;

            // name
            if (parseLine.startsWith("--")) {
                currentOption = findLongOption(processedCommand, parseLine.substring(2));
                if (currentOption != null)
                    currentOption.setLongNameUsed(true);
            }
            else if (parseLine.startsWith("-")) {
                currentOption = findOption(processedCommand, parseLine.substring(1, 2));
                if (currentOption != null)
                    currentOption.setLongNameUsed(false);
            }

            // new option
            if (currentOption != null) {
                if (currentOption.isLongNameUsed()) {
                    // make sure that we dont have any "active" options lying around
                    if (active != null) {
                        if (active.getOptionType() == OptionType.LIST ||
                            active.getOptionType() == OptionType.GROUP) {
                            commandLine.addOption(active);
                            active = null;
                        }
                        else if(active.getDefaultValues() != null && active.getDefaultValues().size() > 0) {
                            active.addValue(active.getDefaultValues().get(0));
                            commandLine.addOption(active);
                            active = null;
                        }
                        else {
                            commandLine.setParserException(new OptionParserException("Option: " + active.getDisplayName() + " must be given a value"));
                            break;
                        }
                    }

                    active = currentOption;
                    if (active.isProperty()) {
                        if (parseLine.length() <= (2 + active.getName().length()) ||
                            !parseLine.contains(EQUALS))
                            commandLine.setParserException(new OptionParserException(
                                "Option " + active.getDisplayName() + ", must be part of a property"));
                        else {
                            String name =
                                parseLine.substring(2 + active.getName().length(),
                                    parseLine.indexOf(EQUALS));
                            String value = parseLine.substring(parseLine.indexOf(EQUALS) + 1);
                            if (value.length() < 1)
                                commandLine.setParserException(new OptionParserException("Option " + active.getDisplayName() + ", must have a value"));
                            else {
                                active.addProperty(name, value);
                                commandLine.addOption(active);
                                active = null;
                                if (addedArgument)
                                    commandLine.setParserException(new ArgumentParserException("An argument was given to an option that does not support it."));
                            }
                        }
                    }
                    else if (active.getValue() != null) {
                        if (!active.getEndsWithSeparator()) {
                            commandLine.addOption(active);
                            active = null;
                        }
                    }
                    else if (active.getOptionType().equals(OptionType.BOOLEAN) &&
                        (!active.hasValue() || active.getValue() != null)) {
                        active.addValue("true");
                        commandLine.addOption(active);
                        active = null;
                        if (addedArgument)
                            commandLine.setParserException(new ArgumentParserException("An argument was given to an option that does not support it."));
                    }
                    else if (active == null)
                        commandLine.setParserException(new OptionParserException("Option: " + parseLine + " is not a valid option for this command"));
                }
                // name
                else if (!currentOption.isLongNameUsed()) {
                    // make sure that we dont have any "active" options lying around
                    // except list and group
                    if (active != null) {
                        if (active.getOptionType() == OptionType.LIST ||
                            active.getOptionType() == OptionType.GROUP) {
                            commandLine.addOption(active);
                            active = null;
                        }
                        else if(active.getDefaultValues() != null && active.getDefaultValues().size() > 0) {
                            active.addValue(active.getDefaultValues().get(0));
                            commandLine.addOption(active);
                            active = null;
                        }
                        else {
                            commandLine.setParserException(new OptionParserException("Option: " + active.getDisplayName() + " must be given a value"));
                            break;
                        }
                    }
                    else if (parseLine.length() != 2 && !parseLine.contains("=")) {
                        // we might have two or more options in a group
                        // if so, we only allow options (boolean) without value
                        if (parseLine.length() > 2) {
                            for (char shortName : parseLine.substring(1).toCharArray()) {
                                active = findOption(processedCommand, String.valueOf(shortName));
                                if (active != null) {
                                    if (!active.hasValue()) {
                                        active.setLongNameUsed(false);
                                        active.addValue("true");
                                        commandLine.addOption(active);
                                    }
                                    else
                                        commandLine.setParserException(new OptionParserException("Option: -" + shortName +
                                            " can not be grouped with other options since it need to be given a value"));
                                }
                                else
                                    commandLine.setParserException(new OptionParserException("Option: -" + shortName + " was not found."));
                            }
                            // make sure to reset active
                            active = null;
                        }
                        else
                            commandLine.setParserException(new OptionParserException("Option: - must be followed by a valid operator"));
                    }
                    else {
                        active = findOption(processedCommand, parseLine.substring(1));
                        if (active != null)
                            active.setLongNameUsed(false);

                        if (active != null && active.isProperty()) {
                            if (parseLine.length() <= 2 ||
                                !parseLine.contains(EQUALS))
                                commandLine.setParserException(new OptionParserException(
                                    "Option " + active.getDisplayName() + ", must be part of a property"));
                            else {
                                String name =
                                    parseLine.substring(2, // 2+char.length
                                        parseLine.indexOf(EQUALS));
                                String value = parseLine.substring(parseLine.indexOf(EQUALS) + 1);
                                if (value.length() < 1)
                                    commandLine.setParserException(new OptionParserException("Option " + active.getDisplayName() + ", must have a value"));
                                else {
                                    active.addProperty(name, value);
                                    commandLine.addOption(active);
                                    active = null;
                                    if (addedArgument)
                                        commandLine.setParserException(new OptionParserException("An argument was given to an option that does not support it."));
                                }
                            }
                        }
                        else if (active.getValue() != null) {
                            if (!active.getEndsWithSeparator()) {
                                commandLine.addOption(active);
                                active = null;
                            }
                        }
                        else if (active != null && active.getOptionType().equals(OptionType.BOOLEAN) &&
                            (!active.hasValue() || active.getValue() != null)) {
                            active.addValue("true");
                            commandLine.addOption(active);
                            active = null;
                            if (addedArgument)
                                commandLine.setParserException(new OptionParserException("An argument was given to an option that does not support it."));
                        }
                        else if (active == null)
                            commandLine.setParserException(new OptionParserException("Option: " + parseLine + " is not a valid option for this command"));
                    }
                }
            }
            else if(active != null) {
                if(active.hasMultipleValues()) {
                    if(parseLine.contains(String.valueOf(active.getValueSeparator()))) {
                        for(String value : parseLine.split(String.valueOf(active.getValueSeparator()))) {
                            active.addValue(value.trim());
                        }
                        if(parseLine.endsWith(String.valueOf(active.getValueSeparator())))
                            active.setEndsWithSeparator(true);
                        commandLine.addOption(active);
                        active = null;
                    }
                    else
                        active.addValue(parseLine);
                }
                else
                    active.addValue(parseLine);

                if(active != null &&
                        (active.getOptionType() == OptionType.NORMAL ||
                                active.getOptionType() == OptionType.BOOLEAN)) {
                    commandLine.addOption(active);
                    active = null;
                }
                if(addedArgument)
                    commandLine.setParserException(new OptionParserException("An argument was given to an option that does not support it."));
            }
            //if no command is "active", it is a wrong option or we add it as an argument
            else {
                if (parseLine.startsWith("-")) {
                    commandLine.setParserException(new OptionParserException("Option: " + parseLine + " was not found."));
                }
                else if (processedCommand.getArgument() == null) {
                    commandLine.setParserException(new OptionParserException("An argument was given to a command that does not support it."));
                }
                else {
                    commandLine.addArgumentValue(parseLine);
                    addedArgument = true;
                }
            }
        }

        if(active != null && (ignoreRequirements ||
                (active.getOptionType() == OptionType.LIST || active.getOptionType() == OptionType.GROUP))) {
            commandLine.addOption(active);
        }
        else if(active != null && active.getOptionType() == OptionType.NORMAL &&
                active.hasDefaultValue() && active.hasValue()) {
            active.addValue(active.getDefaultValues().get(0));
            commandLine.addOption(active);
        }

        //this will throw and CommandLineParserException if needed
        if(!ignoreRequirements) {
            RequiredOptionException re = checkForMissingRequiredOptions(processedCommand, commandLine);
            if(re != null)
                commandLine.setParserException(re);
        }

        return commandLine;
    }

    private RequiredOptionException checkForMissingRequiredOptions(ProcessedCommand<C> command,
                                                                   CommandLine<? extends Command> commandLine) {
        for(ProcessedOption o : command.getOptions())
            if(o.isRequired()) {
                boolean found = false;
                for(ProcessedOption po : commandLine.getOptions()) {
                    if(po.getShortName() != null && o.getShortName() != null &&
                            po.getShortName().equals(o.getShortName()) ||
                            (po.getName() != null && po.getName().equals(o.getName()))) {
                        found = true;
                        break;
                    }
                    else if(po.doOverrideRequired()) {
                        found = true;
                        break;
                    }
                }
                if(!found)
                    return new RequiredOptionException("Option: "+o.getDisplayName()+" is required for this command.");
            }
        return null;
    }

    private ProcessedOption findOption(ProcessedCommand command, String line) {
        ProcessedOption option = command.findOption(line);
        //simplest case
        if(option != null)
            return option;

        option = command.startWithOption(line);
        //if its a property, we'll parse it later
        if(option != null && option.isProperty())
            return option;
        if(option != null) {
           String rest = line.substring(option.getShortName().length());
            if(rest.length() > 1 && rest.startsWith("=")) {
                if(option.getOptionType().equals(OptionType.LIST) &&
                        rest.indexOf(option.getValueSeparator()) > -1) {
                    for(String value : rest.substring(1).split(String.valueOf(option.getValueSeparator()))) {
                        option.addValue(value.trim());
                    }
                    if(rest.endsWith(String.valueOf(option.getValueSeparator())))
                        option.setEndsWithSeparator(true);
                }
                else
                    option.addValue(rest.substring(1));
                return option;
            }
        }

        return null;
    }

    private ProcessedOption findLongOption(ProcessedCommand command, String line) {
        ProcessedOption option = command.findLongOptionNoActivatorCheck(line);
        //simplest case
        if(option != null)
            return option;

        option = command.startWithLongOption(line);
        //if its a property, we'll parse it later
        if(option != null && option.isProperty())
            return option;
        if(option != null) {
            String rest = line.substring(option.getName().length());
            if(rest.length() > 1 && rest.startsWith("=")) {
                 if(option.getOptionType().equals(OptionType.LIST) &&
                        rest.indexOf(option.getValueSeparator()) > -1) {
                    for(String value : rest.substring(1).split(String.valueOf(option.getValueSeparator()))) {
                        option.addValue(value.trim());
                    }
                    if(rest.endsWith(String.valueOf(option.getValueSeparator())))
                        option.setEndsWithSeparator(true);
                }
                else
                     option.addValue(rest.substring(1));
                return option;
            }
        }

        return null;
    }

    @Override
    public void clear() {
        processedCommand.clear();
        if(isGroupCommand()) {
            for (CommandLineParser child : getChildParsers())
                child.getProcessedCommand().clear();
        }
    }

    @Override
    public boolean isGroupCommand() {
        List<CommandLineParser<? extends Command>> parsers = getChildParsers();
        return parsers != null && parsers.size() > 0;
    }

    @Override
    public String toString() {
        return "CommandLineParser{" +
                "processedCommand=" + processedCommand +
                "command=" + processedCommand.getCommand() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AeshCommandLineParser)) return false;

        AeshCommandLineParser that = (AeshCommandLineParser) o;

        return processedCommand.equals(that.processedCommand);

    }

    @Override
    public int hashCode() {
        return processedCommand.hashCode();
    }
}
