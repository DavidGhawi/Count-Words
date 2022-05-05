package com.cm6121.countWord.app.utilityFile;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cm6121.countWord.app.utilityFile.FileReader.completeMap;

public class SortMap {
    public static Map<String, Integer> sortMap(HashMap<String, Integer> unsortedMap, String order) {
        if (order.equals("ASC")) {
            Map<String, Integer> sortedMap = unsortedMap.entrySet().stream()
                    .sorted(Comparator.comparingInt(e -> e.getValue()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> {
                                throw new AssertionError();
                            },
                            LinkedHashMap::new
                    ));
            return sortedMap;
        }
        if (order.equals("DSC")) {
            LinkedHashMap<String, Integer> reverseCompleteMap = new LinkedHashMap<>();
            completeMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseCompleteMap.put(x.getKey(), x.getValue()));
            return reverseCompleteMap;
        }

        return null;
    }
}
