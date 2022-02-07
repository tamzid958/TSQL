package com.sql.tsql.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class DBService {
    @Value("${db-path}")
    private String dbPath;

    public Boolean init() {
        var server = new File(dbPath);

        if (!server.exists() && !server.isDirectory() && !server.mkdirs()) {
            log.error("Server initialization failed.");
            return false;
        }
        return true;
    }
}
