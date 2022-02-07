package com.sql.tsql.Helpers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ParserHelper() {
    public List<String> parseWrappedObject(List<String> parsedCommand) {
        return parsedCommand.stream()
                .skip(parsedCommand.indexOf(Holder.START))
                .limit(parsedCommand.indexOf(Holder.END))
                .filter(column -> !Holder.SKIP.contains(column))
                .toList();
    }
}
