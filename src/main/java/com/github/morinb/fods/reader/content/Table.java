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

package com.github.morinb.fods.reader.content;

import com.github.morinb.fods.reader.content.cell.Cell;
import com.github.morinb.fods.reader.exceptions.InvalidCoordinatesException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Builder
public class Table {
    String name;
    long numberOfColumns;

    @Singular()
    List<List<Cell>> rows;

    public Cell getCellAt(int row, int col) {
        if (row > rows.size() || col > numberOfColumns) {
            throw new InvalidCoordinatesException(row, col, rows.size()
                    , numberOfColumns);
        }

        return rows.get(row - 1).get(col - 1);
    }

    public String getValueAt(int row, int col) {
        return getCellAt(row, col).getText();
    }
}
