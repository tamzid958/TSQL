package com.sql.tsql.Services;

import com.sql.tsql.Helpers.ParserHelper;
import com.sql.tsql.Models.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Parser {
    private final ParserHelper parserHelper;

    public Object start(String command) {
        var parsedCommand = Arrays.stream(command.split("\\s+")).toList();

        if (parsedCommand.size() <= 1) throw new RuntimeException("invalid command");

        var operation = parsedCommand.get(0).toUpperCase();
        var context = parsedCommand.get(1).toUpperCase();

        if ("CREATE".equals(operation)) {
            if ("TABLE".equals(context)) {
                var tableName = parsedCommand.get(2);
                List<String> columns = parserHelper.parseWrappedObject(parsedCommand);
                return new Table(tableName, columns);
            }
            throw new RuntimeException("Unexpected token: " + context);
        }
        throw new RuntimeException("Unexpected token: " + context);
    }
}
