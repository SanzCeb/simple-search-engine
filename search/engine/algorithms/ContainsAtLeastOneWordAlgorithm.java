package search.engine.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class ContainsAtLeastOneWordAlgorithm extends SimpleSearchStrategy {

    public ContainsAtLeastOneWordAlgorithm(List<String> data) {
        super(data);
    }


    @Override
    public Stream<String[]> matchData(String searchKey) {
        var searchKeyWords = searchKey.split(" ");
        for (var searchKeyWord : searchKeyWords) {
            if (!invertedIndexes.containsKey(searchKeyWord)) {
            for (int i = 0; i < data.size(); i++) {
                    var indexFound = findIndex(data.get(i), searchKeyWord);
                    if (indexFound != -1) {
                        invertedIndexes.putIfAbsent(searchKeyWord, new HashSet<>());
                        invertedIndexes.get(searchKeyWord).add(i);
                    }
                }
            }
        }
        return  Arrays.stream(searchKeyWords)
                .flatMap(searchKeyWord -> invertedIndexes.getOrDefault(searchKeyWord, Collections.emptySet()).stream())
                .distinct()
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
