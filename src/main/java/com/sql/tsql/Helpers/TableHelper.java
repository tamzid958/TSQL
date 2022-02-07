package com.sql.tsql.Helpers;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TableHelper {
    public String[] listTables(String db) {
        var tables = Stream.of(Objects.requireNonNull(new File(db).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());

        String[] arrayOfString = new String[tables.size()];

        int index = 0;
        for (String str : tables)
            arrayOfString[index++] = str;

        // return the formed String[]
        return arrayOfString;
    }
}
