package com.sql.tsql.Helpers;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class TableHelper {
    public HashMap<String, String> listTables(String db) {
        HashMap<String, String> tableInfo = new HashMap<>();

        Stream.of(Objects.requireNonNull(new File(db).listFiles()))
                .filter(file -> !file.isDirectory() && file.getName().contains(".table"))
                .distinct()
                .forEach(file -> {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();
                        tableInfo.put(
                                String.valueOf(file)
                                        .replace(db, "")
                                        .replace(".table", ""),
                                String.valueOf(lines - 1)
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return tableInfo;
    }
}
