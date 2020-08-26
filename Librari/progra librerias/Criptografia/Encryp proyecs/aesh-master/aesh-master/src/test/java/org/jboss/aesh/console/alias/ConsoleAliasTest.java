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
package org.jboss.aesh.console.alias;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.jboss.aesh.console.BaseConsoleTest;
import org.jboss.aesh.console.Config;
import org.jboss.aesh.console.Console;
import org.jboss.aesh.console.ConsoleOperation;
import org.jboss.aesh.console.settings.SettingsBuilder;
import org.junit.Test;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class ConsoleAliasTest extends BaseConsoleTest {


    @Test
    public void alias() throws Exception {
        SettingsBuilder builder = new SettingsBuilder();
        builder.enableAlias(true);
        builder.persistAlias(false);
        builder.aliasFile(Config.isOSPOSIXCompatible() ?
                new File("src/test/resources/alias1") : new File("src\\test\\resources\\alias1"));

        invokeTestConsole(2, new Setup() {
            @Override
            public void call(Console console, OutputStream out) throws IOException {
                out.write(("ll"+Config.getLineSeparator()).getBytes());
                out.flush();
                out.write(("grep -l"+Config.getLineSeparator()).getBytes());
            }
        }, new Verify() {
           private int count = 0;
           @Override
           public int call(Console console, ConsoleOperation op) {
               if(count == 0)
                   assertEquals("ls -alF", op.getBuffer());
               else if(count == 1) {
                   assertEquals("grep --color=auto -l", op.getBuffer());
               }
               count++;
               return 0;
           }
        }, builder);
    }
}
