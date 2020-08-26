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
package org.jboss.aesh.readline.editing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jboss.aesh.console.AeshConsoleBufferBuilder;
import org.jboss.aesh.console.AeshInputProcessorBuilder;
import org.jboss.aesh.console.ConsoleBuffer;
import org.jboss.aesh.console.InputProcessor;
import org.jboss.aesh.console.Prompt;
import org.jboss.aesh.console.Shell;
import org.jboss.aesh.console.TestShell;
import org.jboss.aesh.console.settings.Settings;
import org.jboss.aesh.console.settings.SettingsBuilder;
import org.jboss.aesh.terminal.Key;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class ViModeTest {

    @Test
    public void testSimpleMovementAndEdit() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("abcd");

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.x);

        assertEquals("abc", consoleBuffer.getBuffer().getLineNoMask());

        inputProcessor.parseOperation(Key.h);
        inputProcessor.parseOperation(Key.s);
        inputProcessor.parseOperation(Key.T);

        assertEquals("aTc", consoleBuffer.getBuffer().getLineNoMask());

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.ZERO);
        inputProcessor.parseOperation(Key.x);

        assertEquals("Tc", consoleBuffer.getBuffer().getLineNoMask());

        inputProcessor.parseOperation(Key.l);
        inputProcessor.parseOperation(Key.a);
        inputProcessor.parseOperation(Key.o);

        assertEquals("Tco", consoleBuffer.getBuffer().getLineNoMask());
    }

    @Test
    public void testWordMovementAndEdit() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("  ..");

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.b);
        inputProcessor.parseOperation(Key.x);

        assertEquals("  .", consoleBuffer.getBuffer().getLineNoMask());

        inputProcessor.parseOperation(Key.ENTER);

        consoleBuffer.writeString("foo bar");
        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.ZERO);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.x);

        assertEquals("foo ar", consoleBuffer.getBuffer().getLineNoMask());
    }

    @Test
    public void testWordMovementAndEdit2() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("foo  bar...  Foo-Bar.");

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.B);
        inputProcessor.parseOperation(Key.d);
        inputProcessor.parseOperation(Key.b);

        assertEquals("foo  barFoo-Bar.", consoleBuffer.getBuffer().getLineNoMask());

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.ZERO);
        inputProcessor.parseOperation(Key.W);
        inputProcessor.parseOperation(Key.d);
        inputProcessor.parseOperation(Key.w);

        assertEquals("foo  -Bar.", consoleBuffer.getBuffer().getLineNoMask());
    }

    @Test
    public void testRepeatAndEdit() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("/cd /home/foo/ ls/ cd Desktop/ ls ../");

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.ZERO);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.c);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.b);
        inputProcessor.parseOperation(Key.a);
        inputProcessor.parseOperation(Key.r);
        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.W);
        inputProcessor.parseOperation(Key.d);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.PERIOD);

        assertEquals("/cd /home/bar/ cd Desktop/ ls ../",
                inputProcessor.parseOperation(Key.ENTER));

        consoleBuffer.writeString("/cd /home/foo/ ls/ cd Desktop/ ls ../");
        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.B);
        inputProcessor.parseOperation(Key.D);
        inputProcessor.parseOperation(Key.B);
        inputProcessor.parseOperation(Key.PERIOD);
        inputProcessor.parseOperation(Key.B);
        inputProcessor.parseOperation(Key.PERIOD);

        assertEquals("/cd /home/foo/ ls/ cd ",
                inputProcessor.parseOperation(Key.ENTER));
    }

    @Test
    public void testTildeAndEdit() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("apt-get install vIM");
        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.b);
        inputProcessor.parseOperation(Key.TILDE);
        inputProcessor.parseOperation(Key.TILDE);
        inputProcessor.parseOperation(Key.TILDE);

        assertEquals("apt-get install Vim", consoleBuffer.getBuffer().getLine());

        inputProcessor.parseOperation(Key.ZERO);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.c);
        inputProcessor.parseOperation(Key.w);

        consoleBuffer.writeString("cache");

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.c);
        inputProcessor.parseOperation(Key.w);

        consoleBuffer.writeString("search");

        assertEquals("apt-cache search Vim",
                inputProcessor.parseOperation(Key.ENTER));

    }

    @Test
    public void testPasteAndEdit() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("apt-get install vIM");

        inputProcessor.parseOperation(Key.ESC);
        inputProcessor.parseOperation(Key.ZERO);
        inputProcessor.parseOperation(Key.d);
        inputProcessor.parseOperation(Key.W);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.P);
        inputProcessor.parseOperation(Key.W);
        inputProcessor.parseOperation(Key.y);
        inputProcessor.parseOperation(Key.w);
        inputProcessor.parseOperation(Key.DOLLAR);
        inputProcessor.parseOperation(Key.p);

        assertEquals("install apt-get vIMvIM",
                inputProcessor.parseOperation(Key.ENTER));
    }

    @Test
    public void testSearch() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .persistHistory(false)
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("asdf jkl");
        inputProcessor.parseOperation(Key.ENTER);

        consoleBuffer.writeString("footing");
        inputProcessor.parseOperation(Key.ENTER);

        inputProcessor.parseOperation(Key.CTRL_R);
        inputProcessor.parseOperation(Key.a);

        assertEquals("asdf jkl",
        inputProcessor.parseOperation(Key.ENTER));

    }

    @Test
    public void testSearchWithArrownRight() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .persistHistory(false)
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("asdf jkl");
        inputProcessor.parseOperation(Key.ENTER);

        consoleBuffer.writeString("footing");
        inputProcessor.parseOperation(Key.ENTER);

        inputProcessor.parseOperation(Key.CTRL_R);
        inputProcessor.parseOperation(Key.a);
        inputProcessor.parseOperation(Key.RIGHT);

        assertEquals("asdf jkl", consoleBuffer.getBuffer().getLine());
    }

    @Test
    public void testSearchWithArrownLeft() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .persistHistory(false)
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("asdf jkl");
        inputProcessor.parseOperation(Key.ENTER);

        consoleBuffer.writeString("footing");
        inputProcessor.parseOperation(Key.ENTER);

        inputProcessor.parseOperation(Key.CTRL_R);
        inputProcessor.parseOperation(Key.a);
        inputProcessor.parseOperation(Key.LEFT);

        assertEquals("asdf jkl", consoleBuffer.getBuffer().getLine());

    }

    @Test
    public void testSearchWithArrownUp() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .persistHistory(false)
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("footing");
        inputProcessor.parseOperation(Key.ENTER);

        consoleBuffer.writeString("asdf jkl");
        inputProcessor.parseOperation(Key.ENTER);

        inputProcessor.parseOperation(Key.CTRL_R);
        inputProcessor.parseOperation(Key.a);
        inputProcessor.parseOperation(Key.UP);

        assertEquals("footing", consoleBuffer.getBuffer().getLine());

    }

    @Test
    public void testSearchWithArrownDown() throws IOException, InterruptedException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Settings settings = new SettingsBuilder()
                .persistHistory(false)
                .readInputrc(false)
                .ansi(true)
                .enableAlias(false)
                .mode(EditMode.Mode.VI)
                .create();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder()
                .shell(shell)
                .prompt(new Prompt("aesh"))
                .editMode(settings.getEditMode())
                .create();

        InputProcessor inputProcessor = new AeshInputProcessorBuilder()
                .consoleBuffer(consoleBuffer)
                .settings(settings)
                .create();

        consoleBuffer.writeString("asdf jkl");
        inputProcessor.parseOperation(Key.ENTER);

        consoleBuffer.writeString("footing");
        inputProcessor.parseOperation(Key.ENTER);

        inputProcessor.parseOperation(Key.CTRL_R);
        inputProcessor.parseOperation(Key.a);
        inputProcessor.parseOperation(Key.DOWN);
        assertEquals("footing", consoleBuffer.getBuffer().getLine());
    }

}
