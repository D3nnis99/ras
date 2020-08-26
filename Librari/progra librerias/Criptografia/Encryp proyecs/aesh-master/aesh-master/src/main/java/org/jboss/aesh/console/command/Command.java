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
package org.jboss.aesh.console.command;

import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.io.IOException;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
@FunctionalInterface
public interface Command<T extends CommandInvocation> {

    /**
     * Will be executed when this command is triggered by the command line.
     *
     * @param commandInvocation invocation
     * @return success or failure depending on how the execution went.
     * @throws IOException
     * @throws InterruptedException
     */
    CommandResult execute(T commandInvocation) throws IOException, InterruptedException;
}