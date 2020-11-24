package search.engine.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class NotContainsAnyWordAlgorithm extends  SimpleSearchStrategy {

    public NotContainsAnyWordAlgorithm(List<String> data) {
        super(data);
    }

    public Stream<String[]> matchData(String searchKey) {
        var searchKeyWords = searchKey.split(" ");
        if ( !invertedIndexes.containsKey(searchKey)) {
            for (int i = 0; i < data.size(); i++) {
                var notContainsAnyWord = true;
                for (var searchKeyWord : searchKeyWords) {
                    notContainsAnyWord = notContainsAnyWord && findIndex(data.get(i), searchKeyWord) == -1;
                }
                if (notContainsAnyWord) {
                    invertedIndexes.putIfAbsent(searchKey, new HashSet<>());
                    invertedIndexes.get(searchKey).add(i);
                }
            }
        }

        return invertedIndexes
                .getOrDefault(searchKey, Collections.emptySet())
                .stream()
                .map(data::get);
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
}
