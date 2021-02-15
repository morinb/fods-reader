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

package com.github.morinb.fods.reader;

import com.github.morinb.fods.reader.exceptions.Logger;
import org.slf4j.MDC;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

/**
 * FODS Spreadsheet
 */

public class Spreadsheet {
    private static final Logger LOGGER = new Logger();

    public Spreadsheet(InputStream inputStream) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();
            System.out.println("Root element : "+ document.getDocumentElement().getNodeName());

        } catch (IllegalArgumentException|ParserConfigurationException | SAXException | IOException e) {
            MDC.put("UUID", "[" + UUID.randomUUID().toString() + "]");
            LOGGER.error(unused -> MethodHandles.lookup().lookupClass().getSimpleName());
        }
    }

}
