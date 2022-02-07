package com.sql.tsql.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServerService {
    private static final String serverPath = "server/";

    @Bean
    public Boolean init() {
        var server = new File(serverPath);

        if (!server.exists() && !server.isDirectory() && !server.mkdirs()) {
            log.error("Server initialization failed.");
            return false;
        }
        return true;
    }

    @Bean
    public String getServerPath() {
        return serverPath;
    }
}
