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
package org.jboss.aesh.console.command.registry;

import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.container.AeshCommandContainerBuilder;
import org.jboss.aesh.console.command.container.CommandContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Only used by AeshConsoleImpl to store built-in commands
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class AeshInternalCommandRegistry {

    private final Map<String, CommandContainer> registry = new HashMap<String, CommandContainer>();

    public void addCommand(Command command) {
        putIntoRegistry(new AeshCommandContainerBuilder().create(command));
    }

    private void putIntoRegistry(CommandContainer commandContainer) {
        if(!commandContainer.haveBuildError() &&
                !registry.containsKey(commandContainer.getParser().getProcessedCommand().getName()))
            registry.put(commandContainer.getParser().getProcessedCommand().getName(), commandContainer);
    }

    public CommandContainer getCommand(String name) {
        return registry.get(name);
    }

    public Set<String> getAllCommandNames() {
        return registry.keySet();
    }
}
