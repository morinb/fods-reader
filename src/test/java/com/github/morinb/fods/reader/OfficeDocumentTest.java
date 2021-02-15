/*
 * Copyright 2021 Baptiste
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

import com.github.morinb.fods.reader.content.Table;
import com.github.morinb.fods.reader.exceptions.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class OfficeDocumentTest {
    private static Logger LOGGER = new Logger();

    @Test
    public void testSimpleCells() {
        try (InputStream simpleCellsFods = getClass().getResourceAsStream("/fods/simple/SimpleCells.fods")) {

            OfficeDocument officeDocument = new OfficeDocument(simpleCellsFods);

            Assertions.assertNotNull(officeDocument);
            Assertions.assertNotNull(officeDocument.getBody());
            Assertions.assertNotNull(officeDocument.getBody().getSpreadsheet());

            final Table feuille1 = officeDocument.getBody().getSpreadsheet().getByName("Feuille1").orElse(null);
            Assertions.assertNotNull(feuille1);
            Assertions.assertEquals("Feuille1", feuille1.getName());
            Assertions.assertEquals(6, feuille1.getNumberOfColumns());
            Assertions.assertEquals(6, feuille1.getRows().size());
            Assertions.assertEquals("A1", feuille1.getValueAt(0, 0));
            Assertions.assertEquals("B1", feuille1.getValueAt(0, 1));
            Assertions.assertEquals("B2", feuille1.getValueAt(1, 1));
            Assertions.assertEquals("C2", feuille1.getValueAt(1, 2));
            Assertions.assertEquals("D3", feuille1.getValueAt(2, 3));
            Assertions.assertEquals("D4", feuille1.getValueAt(3, 3));
            Assertions.assertEquals("F6", feuille1.getValueAt(5, 5));

        } catch (IOException e) {
            LOGGER.error(unused -> "", e);
        }
    }
}