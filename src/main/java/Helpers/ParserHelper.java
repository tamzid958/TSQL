package Helpers;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ParserHelper {
    public List<String> parseWrappedObject(List<String> parsedCommand) {
        return parsedCommand.stream()
                .skip(parsedCommand.indexOf(Holder.START))
                .limit(parsedCommand.indexOf(Holder.END))
                .filter(column -> !Holder.SKIP.contains(column))
                .toList();
    }
}
