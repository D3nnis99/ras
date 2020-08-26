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
package org.jboss.aesh.console.aesh;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.internal.ProcessedCommandBuilder;
import org.jboss.aesh.cl.parser.CommandLineParserException;
import org.jboss.aesh.cl.internal.ProcessedCommand;
import org.jboss.aesh.cl.result.ResultHandler;
import org.jboss.aesh.console.AeshConsole;
import org.jboss.aesh.console.AeshConsoleBuilder;
import org.jboss.aesh.console.Config;
import org.jboss.aesh.console.Prompt;
import org.jboss.aesh.console.Shell;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.console.command.registry.AeshCommandRegistryBuilder;
import org.jboss.aesh.console.command.registry.CommandRegistry;
import org.jboss.aesh.console.settings.CommandNotFoundHandler;
import org.jboss.aesh.console.settings.Settings;
import org.jboss.aesh.console.settings.SettingsBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * the idea of this test is to show how aesh could work reading a script file.
 * this impl will only accept "foo" commands,
 * if any other commands are found it should jump out and exit.
 *
 * the scriptfile:
 * foo //this is accepted
 * foo // also accepted
 *
 * exit //should exit
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class AeshScriptTest {

    private static CountDownLatch counter = new CountDownLatch(3);

    @Test
    public void scriptPoc() throws IOException, CommandLineParserException, InterruptedException {

        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(outputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

          Settings settings = new SettingsBuilder()
                .inputStream(pipedInputStream)
                .outputStream(new PrintStream(byteArrayOutputStream))
                .logging(true)
                .create();


        CommandResultHandler resultHandler = new CommandResultHandler();
        ProcessedCommand fooCommand = new ProcessedCommandBuilder()
                .name("foo")
                .resultHandler(resultHandler)
                .command(FooCommand.class)
                .create();

        CommandRegistry registry = new AeshCommandRegistryBuilder()
                .command(fooCommand)
                .command(BarCommand.class)
                .command(new RunCommand(resultHandler))
                .command(ExitCommand.class)
                .create();

        AeshConsoleBuilder consoleBuilder = new AeshConsoleBuilder()
                .settings(settings)
                .commandRegistry(registry)
                .commandNotFoundHandler(resultHandler)
                .prompt(new Prompt(""));

        AeshConsole aeshConsole = consoleBuilder.create();

        aeshConsole.start();

        outputStream.write("run".getBytes());
        outputStream.write(Config.getLineSeparator().getBytes());
        outputStream.flush();

        counter.await(1, TimeUnit.SECONDS);

        assertEquals(0, counter.getCount());
    }

    private List<String> readScriptFile() throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/script1"));
        String line = br.readLine();
        while (line != null) {
            if (line.trim().length() > 0 && !line.trim().startsWith("#"))
                lines.add(line);
            line = br.readLine();
        }

        return lines;
    }

    @CommandDefinition(name = "run", description = "")
    private class RunCommand implements Command {

        private final CommandResultHandler resultHandler;

        public RunCommand(CommandResultHandler resultHandler) {
            this.resultHandler = resultHandler;
        }

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {

//            commandInvocation.putProcessInBackground();

            List<String> script = readScriptFile();

            for(String line : script) {
                commandInvocation.executeCommand(line + Config.getLineSeparator());
            }

            return CommandResult.SUCCESS;
        }
    }

    @CommandDefinition(name ="exit", description = "")
    private static class ExitCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {
            commandInvocation.getShell().out().println("EXITING");
            commandInvocation.stop();
            return CommandResult.SUCCESS;
        }
    }

    @CommandDefinition(name = "foo", description = "")
    private static class FooCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {
            if(counter.getCount() > 1) {
                commandInvocation.getShell().out().println("computing...." + Config.getLineSeparator() + "finished computing, returning...");
                counter.countDown();
                return CommandResult.SUCCESS;
            }
            else {
                assertTrue(false);
                return CommandResult.FAILURE;
            }
        }
    }

    @CommandDefinition(name = "bar", description = "")
    private static class BarCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {
            if(counter.getCount() == 1) {
                commandInvocation.getShell().out().println("baring...." + Config.getLineSeparator() + "finished baring, returning...");
                counter.countDown();
                return CommandResult.SUCCESS;
            }
            else {
                assertTrue(false);
                return CommandResult.FAILURE;
            }
        }
    }

    private static class CommandResultHandler implements ResultHandler, CommandNotFoundHandler {

        private boolean failed = false;
        private String failedString;

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailure(CommandResult result) {
            failed = true;
        }

        @Override
        public void onValidationFailure(CommandResult result, Exception exception) {
            failed = true;
            failedString = exception.toString();
        }

        public void setFailed(boolean f) {
            failed = f;
        }

        public boolean hasFailed() {
            return failed;
        }

        public String getFailedReason() {
            return failedString;
        }

        @Override
        public void handleCommandNotFound(String line, Shell shell) {
            failed = true;
            failedString = line;
        }
    }

}
