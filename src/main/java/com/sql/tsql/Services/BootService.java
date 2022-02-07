package com.sql.tsql.Services;

import com.sql.tsql.Helpers.AvailableOperations;
import com.sql.tsql.Helpers.TableHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;


@Service
@Slf4j
@RequiredArgsConstructor
public class BootService {
    private static final Scanner input = new Scanner(System.in);
    private final ServerService serverService;
    private final Parser parser;
    private final TableHelper tableHelper;
    @Value("${server-path}")
    private String serverPath;

    public void start() throws IOException {
        if (serverService.init()) {
            out.println("AVAILABLE OPERATIONS: ");
            AvailableOperations.withExample.forEach((op, example) -> {
                out.println(op + " - " + example);
            });
            
            while (true) {
                var command = input.nextLine();
                parser.start(command);
            }
        }
    }
}
