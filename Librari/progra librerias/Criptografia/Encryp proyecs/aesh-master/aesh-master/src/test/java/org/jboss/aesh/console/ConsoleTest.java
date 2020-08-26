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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class ConsoleTest extends BaseConsoleTest {


    @Test
    public void multiLine() throws Throwable {
        invokeTestConsole(new Setup() {
            @Override
            public void call(Console console, OutputStream out) throws IOException {
                out.write(("ls \\").getBytes());
                out.write((Config.getLineSeparator()).getBytes());
                out.write(("foo \\").getBytes());
                out.write((Config.getLineSeparator()).getBytes());
                out.write(("bar"+Config.getLineSeparator()).getBytes());
                out.flush();
            }
        }, new Verify() {
            @Override
            public int call(Console console, ConsoleOperation op) {
                assertEquals("ls foo bar", op.getBuffer());
                return 0;
            }
        });
    }

}
