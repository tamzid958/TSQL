package com.sql.tsql.Services;

import com.sql.tsql.Helpers.AvailableOperations;
import com.sql.tsql.Helpers.TermTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;


@Service
@Slf4j
public record BootService(DBService dbService, Parser parser) {
    private static final Scanner input = new Scanner(System.in);

    public void start() throws IOException {
        if (dbService.init()) {
            TermTable termTable = new TermTable();
            termTable.setShowVerticalLines(true);
            termTable.setHeaders("available commands", "example");
            AvailableOperations.withExample.forEach(termTable::addRow);
            termTable.print();

            while (true) {
                System.out.print("TSQL > ");
                var command = input.nextLine().trim();
                parser.start(command);
            }
        }
    }
}
