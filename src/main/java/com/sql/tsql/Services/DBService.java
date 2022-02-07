package com.sql.tsql.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class DBService {
    @Value("${server-path}")
    private String serverPath;

    public void create(String name) {
        Path path = Paths.get(serverPath, name);
        var file = new File(String.valueOf(path));
        if (file.mkdirs()) {
            log.info("DB Created");
        } else {
            log.error("DB already exists");
        }
    }
}
