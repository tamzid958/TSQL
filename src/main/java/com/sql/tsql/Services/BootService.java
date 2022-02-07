package com.sql.tsql.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


@Service
@Slf4j
@RequiredArgsConstructor
public class BootService {
    public static List<String> availableOperations = List.of("CREATE DATABASE", "CREATE TABLE");
    private final ServerService serverService;
    private final Parser parser;
    private final Scanner input = new Scanner(System.in);

    public void start() {
        if (serverService.init()) {
            out.println("AVAILABLE OPERATIONS: ");
            availableOperations.forEach(op -> {
                out.println(availableOperations.indexOf(op) + 1 + ". " + op);
            });

            var command = input.nextLine();

            out.println(parser.start(command));
        }
    }
}
