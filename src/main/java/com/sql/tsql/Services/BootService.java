package com.sql.tsql.Services;

import com.sql.tsql.Helpers.AvailableOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;


@Service
@Slf4j
public record BootService(ServerService serverService, Parser parser) {
    private static final Scanner input = new Scanner(System.in);

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
