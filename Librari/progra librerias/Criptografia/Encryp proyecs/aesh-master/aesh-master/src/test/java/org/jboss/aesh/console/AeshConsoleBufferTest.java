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
package org.jboss.aesh.console;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class AeshConsoleBufferTest {

    @Test
    public void testSimpleWrites() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder().shell(shell).prompt(new Prompt("aesh")).create();

        consoleBuffer.displayPrompt();
        assertTrue(byteArrayOutputStream.toString().contains("aesh"));
        byteArrayOutputStream.reset();

        consoleBuffer.writeString("foo");
        assertEquals("foo", byteArrayOutputStream.toString());


        consoleBuffer.writeString("OOO");
        assertEquals("fooOOO", byteArrayOutputStream.toString());
    }

    @Test
    public void testMovement()  throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder().shell(shell).prompt(new Prompt("aesh")).create();

        consoleBuffer.writeString("foo0");
        consoleBuffer.moveCursor(-1);
        assertEquals("foo0" + new String(Buffer.printAnsi("1D")), byteArrayOutputStream.toString());
        consoleBuffer.moveCursor(-10);
        assertEquals("foo0" + new String(Buffer.printAnsi("1D")) + new String(Buffer.printAnsi("3D")), byteArrayOutputStream.toString());

        consoleBuffer.writeString("1");
        assertEquals("1foo0", consoleBuffer.getBuffer().getLine());

        byteArrayOutputStream.reset();
        consoleBuffer.moveCursor(1);
        assertEquals(new String(Buffer.printAnsi("1C")), byteArrayOutputStream.toString());

        consoleBuffer.writeString("2");
        assertEquals("1f2oo0", consoleBuffer.getBuffer().getLine());
    }

    @Test
    public void testDelete()  throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Shell shell = new TestShell(new PrintStream(byteArrayOutputStream), System.err);
        ConsoleBuffer consoleBuffer = new AeshConsoleBufferBuilder().shell(shell).prompt(new Prompt("aesh")).create();

        /* TODO: need more refactoring
        consoleBuffer.writeString("foo0");
        consoleBuffer.performAction(new DeleteAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE));
        assertEquals("foo0", consoleBuffer.getBuffer().getLine());
        consoleBuffer.performAction(new DeleteAction(consoleBuffer.getBuffer().getCursor() - 1, Action.DELETE));
        assertEquals("foo", consoleBuffer.getBuffer().getLine());
        consoleBuffer.performAction(new PrevWordAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE, Mode.EMACS));
        assertEquals("", consoleBuffer.getBuffer().getLine());

        consoleBuffer.writeString("foo bar");
        consoleBuffer.performAction(new PrevSpaceWordAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE));
        assertEquals("foo ", consoleBuffer.getBuffer().getLine());

        consoleBuffer.writeString("foo0 bah");
        //move to the beginning
        consoleBuffer.performAction(new SimpleAction(consoleBuffer.getBuffer().getCursor(), Action.MOVE, 0));

        consoleBuffer.performAction(new NextWordAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE, Mode.EMACS));
        assertEquals("foo0 bah", consoleBuffer.getBuffer().getLine());

        consoleBuffer.performAction(new DeleteAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE));
        assertEquals("oo0 bah", consoleBuffer.getBuffer().getLine());
        consoleBuffer.moveCursor(1);
        consoleBuffer.performAction(new DeleteAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE, true));
        assertEquals("o0 bah", consoleBuffer.getBuffer().getLine());
        consoleBuffer.performAction(new SimpleAction(consoleBuffer.getBuffer().getCursor(), Action.DELETE, consoleBuffer.getBuffer().length()));
        assertEquals("", consoleBuffer.getBuffer().getLine());
        */

    }


}
