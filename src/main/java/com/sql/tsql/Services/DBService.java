package com.sql.tsql.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class DBService {
    private final ServerService serverService;

    public Boolean create(String name) throws IOException {
        Path path = Paths.get(serverService.getServerPath(), name);
        var file = new File(String.valueOf(path));
        if (!file.mkdirs()) {
            log.error("Table already exists");
            return false;
        } else {
            return file.createNewFile();
        }
    }
}
