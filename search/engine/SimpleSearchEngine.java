package search.engine;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleSearchEngine {

    private final List<String[]> data;
    private final Map<String, Set<Integer>> invertedIndexes;

    public SimpleSearchEngine(List<String> data) {
        this.data = parseRecords(data);
        invertedIndexes = new HashMap<>();
    }

    public List<String> search(String searchKey) {
        if (!invertedIndexes.containsKey(searchKey)) {
            for (int i = 0; i < data.size(); i++) {
                var indexFound = findIndex(data.get(i), searchKey);
                if (indexFound != - 1) {
                    invertedIndexes.putIfAbsent(searchKey, new HashSet<>());
                    invertedIndexes.get(searchKey).add(i);
                }
            }
        }

        return getFoundRecords(searchKey);
    }


    private static int findIndex(String[] records, String searchKey) {
        int index = -1;

        for (int i = 0; i < records.length; i++) {
            var record = records[i];
            if (record.equalsIgnoreCase(searchKey)) {
                index = i;
                break;
            }
        }

        return index;
    }

    private List<String[]> parseRecords(List<String> data) {
        return data.stream().map(record -> record.split(" ")).collect(Collectors.toList());
    }

    private List<String> getFoundRecords(String searchKey) {
        return invertedIndexes.getOrDefault(searchKey, Collections.emptySet()).stream()
                .map(data::get)
                .map(words -> String.join(" ", words))
                .collect(Collectors.toList());
    }
}
