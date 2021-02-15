/*
 * Copyright 2021 baptiste
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.morinb.fods.reader.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.function.Function;

/**
 *
 */
@Slf4j
public final class Logger {

    public static final int CLASS_INDEX = 3;

    public void error(Function<Void, String> message, Object... arguments) {
        if (LOGGER.isErrorEnabled()) {
            populateCaller();
            LOGGER.error(String.format(message.apply(null), arguments)
            );
        }
    }

    private void populateCaller() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= CLASS_INDEX) {
            final String className = stackTrace[CLASS_INDEX].getClassName();
            final String methodName = stackTrace[CLASS_INDEX].getMethodName();
            final String fileName = stackTrace[CLASS_INDEX].getFileName();
            final int lineNumber = stackTrace[CLASS_INDEX].getLineNumber();

            MDC.put("LOGGERNAME", className);
            MDC.put("METHODNAME", methodName);
            MDC.put("FILENAME", fileName);
            MDC.put("LINENUMBER", String.valueOf(lineNumber));
        }
    }
}
