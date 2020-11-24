package search.engine.algorithms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SimpleSearchStrategy {
    protected final List<String[]> data;
    protected final Map<String, Set<Integer>> invertedIndexes;

    protected SimpleSearchStrategy(List<String> data) {
        invertedIndexes = new HashMap<>();
        this.data = parseRecords(data);
    }

    public abstract Stream<String[]> matchData(String searchKey);

    private List<String[]> parseRecords(List<String> data) {
        return data.stream().map(record -> record.split(" ")).collect(Collectors.toList());
    }

    protected void addInvertedIndex (String searchKey, int index) {
        invertedIndexes.putIfAbsent(searchKey, new HashSet<>());
        invertedIndexes.get(searchKey).add(index);
    }
}
