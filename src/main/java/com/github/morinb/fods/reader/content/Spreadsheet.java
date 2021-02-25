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

package com.github.morinb.fods.reader.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Spreadsheet {
    CalculationSettings calculationSettings;
    List<Table> tableList;

    public List<String> getTableNames() {
        return tableList.stream().map(Table::getName).collect(Collectors.toList());
    }

    public Optional<Table> getByName(String tableName) {
        return tableList.stream().filter(table -> table.getName().equals(tableName)).findFirst();
    }

    public Table getByIndex(int index) {
        return tableList.get(index);
    }

}
