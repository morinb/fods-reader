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

import com.github.morinb.fods.reader.content.Table;
import com.github.morinb.fods.reader.content.cell.FormulaCell;
import com.github.morinb.fods.reader.exceptions.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OfficeDocumentTest {
    private static final Logger LOGGER = new Logger();

    @Test
    void testSimpleCells() {
        try (InputStream simpleCellsFods = getClass().getResourceAsStream("/fods/simple/SimpleCells.fods")) {

            OfficeDocument officeDocument = new OfficeDocument(simpleCellsFods);

            assertNotNull(officeDocument);
            assertNotNull(officeDocument.getBody());
            assertNotNull(officeDocument.getBody().getSpreadsheet());

            final Table feuille1 = officeDocument.getBody().getSpreadsheet().getByName("Feuille1").orElse(null);
            assertNotNull(feuille1);
            assertEquals("Feuille1", feuille1.getName());
            assertEquals(6, feuille1.getNumberOfColumns());
            assertEquals(6, feuille1.getRows().size());
            assertEquals("A1", feuille1.getValueAt(1, 1));
            assertEquals("B1", feuille1.getValueAt(1, 2));
            assertEquals("B2", feuille1.getValueAt(2, 2));
            assertEquals("C2", feuille1.getValueAt(2, 3));
            assertEquals("D3", feuille1.getValueAt(3, 4));
            assertEquals("D4", feuille1.getValueAt(4, 4));
            assertEquals("F6", feuille1.getValueAt(6, 6));

        } catch (IOException e) {
            LOGGER.error(unused -> "", e);
        }
    }

    @Test
    void testTwoSheets() {
        try (InputStream simpleCellsFods = getClass().getResourceAsStream("/fods/simple/TwoSheets.fods")) {

            OfficeDocument officeDocument = new OfficeDocument(simpleCellsFods);

            assertNotNull(officeDocument);
            assertNotNull(officeDocument.getBody());
            assertNotNull(officeDocument.getBody().getSpreadsheet());

            final Table feuille1 = officeDocument.getBody().getSpreadsheet().getByName("Feuille1").orElse(null);
            assertNotNull(feuille1);
            assertEquals("Feuille1", feuille1.getName());
            assertEquals(4, feuille1.getNumberOfColumns());
            assertEquals(5, feuille1.getRows().size());
            assertEquals("Sp1A1", feuille1.getValueAt(1, 1));
            assertEquals("Sp1D5", feuille1.getValueAt(5, 4));

            final Table feuille2 = officeDocument.getBody().getSpreadsheet().getByName("Feuille2").orElse(null);
            assertNotNull(feuille2);
            assertEquals("Feuille2", feuille2.getName());
            assertEquals(4, feuille2.getNumberOfColumns());
            assertEquals(5, feuille2.getRows().size());
            assertEquals("of:=3+2", ((FormulaCell) feuille2.getCellAt(1, 1)).getFormula());
            assertEquals("5", feuille2.getValueAt(1, 1));
            assertEquals("VRAI", feuille2.getValueAt(1, 2));
            assertEquals("1,00443089430894", feuille2.getValueAt(1, 3));
            assertEquals("Hello", feuille2.getValueAt(1, 4));
            assertEquals("Sp2D5", feuille2.getValueAt(5, 4));

        } catch (IOException e) {
            LOGGER.error(unused -> "", e);
        }
    }
}