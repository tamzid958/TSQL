package com.sql.tsql.Services;

import com.sql.tsql.Helpers.AvailableOperations;
import com.sql.tsql.Helpers.CommandLineTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;


@Service
@Slf4j
@RequiredArgsConstructor
public class BootService {
    private static final Scanner input = new Scanner(System.in);
    private final ServerService serverService;
    private final Parser parser;

    public void start() throws IOException {
        if (serverService.init()) {
            CommandLineTable commandLineTable = new CommandLineTable();
            commandLineTable.setShowVerticalLines(true);
            commandLineTable.setHeaders("available commands", "example");
            AvailableOperations.withExample.forEach(commandLineTable::addRow);
            commandLineTable.print();

            while (true) {
                System.out.print("TSQL > ");
                var command = input.nextLine();
                parser.start(command);
            }
        }
    }
}
