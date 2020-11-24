package search.engine.algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class ContainsAllWordsAlgorithm  extends SimpleSearchStrategy{

    public ContainsAllWordsAlgorithm(List<String> data) {
        super(data);
    }

    @Override
    public Stream<String[]> matchData(String searchKey) {

        var searchKeyWords = searchKey.split(" ");
        if ( !invertedIndexes.containsKey(searchKey)) {
            for (int i = 0; i < data.size(); i++) {
                var containsAllWords = true;
                for (var searchKeyWord : searchKeyWords) {
                    containsAllWords = containsAllWords && findIndex(data.get(i), searchKeyWord) != -1;
                }
                if (containsAllWords) {
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
