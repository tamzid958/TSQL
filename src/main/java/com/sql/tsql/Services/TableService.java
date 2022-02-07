package com.sql.tsql.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TableService {
    @Value("${server-path}")
    private String serverPath;

    public void create(String name) throws IOException {
        File file = getTable(name);
        if (file.createNewFile()) {
            log.info("Table created");
        } else {
            log.error("Table already exists");
        }
    }

    private File getTable(String name) {
        return new File(serverPath + name + ".table");
    }

    public void addColumns(String name, List<String> columns) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(getTable(name), true));
        writer.append(String.join(",", columns));
        writer.close();
    }

    public void delete(String name) {
        File file = getTable(name);
        if (file.delete()) {
            log.info("Table deleted");
        } else {
            log.error("Error deleting " + name);
        }
    }

    public void update(String name, String updatedName) {
        File file = getTable(name);
        File updatedFile = getTable(updatedName);
        if (file.renameTo(updatedFile)) {
            log.info("Table updated");
        } else {
            log.error("Error updating " + name);
        }

    }
}
