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

import org.jboss.aesh.console.command.activator.OptionActivatorProvider;
import org.jboss.aesh.console.command.completer.CompleterInvocationProvider;
import org.jboss.aesh.console.command.converter.ConverterInvocationProvider;
import org.jboss.aesh.console.command.validator.ValidatorInvocationProvider;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class AeshInvocationProviders implements InvocationProviders {

    private final ConverterInvocationProvider converterInvocationProvider;
    private final CompleterInvocationProvider completerInvocationProvider;
    private final ValidatorInvocationProvider validatorInvocationProvider;
    private final OptionActivatorProvider optionActivatorProvider;

    public AeshInvocationProviders(ConverterInvocationProvider converterInvocationProvider,
                                   CompleterInvocationProvider completerInvocationProvider,
                                   ValidatorInvocationProvider validatorInvocationProvider,
                                   OptionActivatorProvider optionActivatorProvider) {
        this.converterInvocationProvider = converterInvocationProvider;
        this.completerInvocationProvider = completerInvocationProvider;
        this.validatorInvocationProvider = validatorInvocationProvider;
        this.optionActivatorProvider = optionActivatorProvider;
    }

    @Override
    public ConverterInvocationProvider getConverterProvider() {
        return converterInvocationProvider;
    }

    @Override
    public CompleterInvocationProvider getCompleterProvider() {
        return completerInvocationProvider;
    }

    @Override
    public ValidatorInvocationProvider getValidatorProvider() {
        return validatorInvocationProvider;
    }

    @Override
    public OptionActivatorProvider getOptionActivatorProvider() {
        return optionActivatorProvider;
    }
}
