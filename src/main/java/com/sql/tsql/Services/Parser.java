package com.sql.tsql.Services;

import com.sql.tsql.Helpers.ParserHelper;
import com.sql.tsql.Models.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public record Parser(ParserHelper parserHelper, TableService tableService) {
    public void start(String command) throws IOException {
        var parsedCommand = Arrays.stream(command.split("\\s+")).toList();

        if (parsedCommand.size() <= 1) {
            log.error("inappropriate command");
            return;
        }

        var operation = parsedCommand.get(0).toUpperCase();
        var context = parsedCommand.get(1).toUpperCase();

        switch (operation) {
            case "CREATE":
                if ("TABLE".equals(context)) {
                    var tableName = parsedCommand.get(2);
                    List<String> columns = parserHelper.parseWrappedObject(parsedCommand);
                    var table = new Table(tableName, columns);
                    tableService.create(table.name());
                    tableService.addColumns(table.name(), table.columns());
                }
                break;
            case "DELETE":
                if ("TABLE".equals(context)) {
                    var tableName = parsedCommand.get(2);
                    var table = new Table(tableName, null);
                    tableService.delete(table.name());
                }
                break;
            default:
                log.error("unexpected token " + operation + " in command: " + command);
        }
    }
}
