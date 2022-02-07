package com.sql.tsql.Services;

import com.sql.tsql.Helpers.ParserHelper;
import com.sql.tsql.Helpers.TableHelper;
import com.sql.tsql.Helpers.TermTable;
import com.sql.tsql.Models.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Parser {
    private final ParserHelper parserHelper;
    private final TableService tableService;
    private final TableHelper tableHelper;
    @Value("${db-path}")
    private String dbPath;

    public void start(String command) throws IOException {
        var parsedCommand = Arrays.stream(command.split("\\s+")).toList();

        var operation = parsedCommand.get(0).toUpperCase();
        var context = parsedCommand.get(1).toUpperCase();
        var tableName = parsedCommand.size() >= 3 ? parsedCommand.get(2) : null;

        switch (operation) {
            case "CREATE":
                if ("TABLE".equals(context)) {
                    List<String> columns = parserHelper.parseWrappedObject(parsedCommand);
                    var table = new Table(tableName, columns);
                    tableService.create(table.name());
                    tableService.addColumns(table.name(), table.columns());
                }
                break;
            case "DELETE":
                if ("TABLE".equals(context)) {
                    var table = new Table(tableName, null);
                    tableService.delete(table.name());
                }
                break;
            case "UPDATE":
                if ("TABLE".equals(context)) {
                    var updatedTableName = parsedCommand.get(3);
                    tableService.update(tableName, updatedTableName);
                }
                break;
            case "SHOW":
                if ("TABLES".equals(context)) {
                    TermTable termTable = new TermTable();
                    termTable.setShowVerticalLines(true);
                    termTable.setHeaders("table", "count");
                    tableHelper.listTables(dbPath).forEach(termTable::addRow);
                    termTable.print();
                }
                break;
            default:
                log.error("unexpected token " + operation + " in command: " + command);
        }
    }
}
