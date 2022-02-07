package com.sql.tsql.Helpers;

import com.sql.tsql.Models.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public record ParserHelper() {
    <T> List<T> addToList(List<T> target, List<T> source) {
        return Stream.concat(target.stream(), source.stream())
                .collect(Collectors.toList());
    }

    Map<String, String> combineListsIntoOrderedMap(List<String> keys, List<String> values) {
        if (keys.size() != values.size())
            throw new IllegalArgumentException("Cannot combine lists with dissimilar sizes");
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    public List<String> parseWrappedObject(List<String> parsedCommand) {
        return addToList(
                List.of("id"),
                parsedCommand.stream()
                        .skip(parsedCommand.indexOf(Holder.START) + 1)
                        .limit(parsedCommand.indexOf(Holder.END))
                        .filter(column -> !Holder.SKIP.contains(column))
                        .peek(column -> {
                            if (column.contains(",") || column.contains("id")) {
                                throw new RuntimeException("corrupted command");
                            }
                        }).toList()

        );
    }

    public Entity createEntity(List<String> parsedCommand) {

        var keys = parsedCommand.stream()
                .skip(parsedCommand.indexOf(Holder.START) + 1)
                .limit(parsedCommand.indexOf("VALUES") - 3)
                .filter(column -> !Holder.SKIP.contains(column))
                .peek(column -> {
                    if (column.contains(",") || column.contains("id")) {
                        throw new RuntimeException("corrupted command");
                    }
                }).toList();

        var values = parsedCommand.stream().skip(parsedCommand.indexOf("VALUES") + 2)
                .limit(parsedCommand.indexOf(Holder.END))
                .filter(column -> !Holder.SKIP.contains(column))
                .peek(column -> {
                    if (column.contains(",")) {
                        throw new RuntimeException("corrupted command");
                    }
                }).toList();

        return new Entity(combineListsIntoOrderedMap(keys, values));
    }
}
