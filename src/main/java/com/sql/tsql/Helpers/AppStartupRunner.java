package com.sql.tsql.Helpers;


import com.sql.tsql.Services.BootService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public record AppStartupRunner(BootService bootService) {
    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        bootService.start();
    }
}