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
package org.jboss.aesh.console.masking;

import static org.junit.Assert.assertEquals;

import org.jboss.aesh.console.BaseConsoleTest;
import org.jboss.aesh.console.Config;
import org.jboss.aesh.console.Prompt;
import org.jboss.aesh.terminal.Key;
import org.junit.Test;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class ConsoleMaskingTest extends BaseConsoleTest {

    @Test
    public void masking() throws Exception {
        invokeTestConsole((console, out) -> {
            Key deletePrevChar =  Key.CTRL_H;
            console.setPrompt(new Prompt("", '\u0000'));

            out.write(("mypassword").getBytes());
            out.write(deletePrevChar.getFirstValue());
            out.write((Config.getLineSeparator()).getBytes());
            out.flush();
        }, (console, op) -> {
            assertEquals("mypasswor", op.getBuffer());
            return 0;
        });
    }
}
