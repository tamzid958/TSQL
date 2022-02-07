package com.sql.tsql.Helpers;


import com.sql.tsql.Services.BootService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner {
    private final BootService bootService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        bootService.start();
    }
}