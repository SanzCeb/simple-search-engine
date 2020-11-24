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
                    if (SimpleLinearSearch.containsWord(data.get(i), searchKeyWord)) {
                        addInvertedIndex(searchKeyWord, i);
                    }
                }
            }
        }
        return  Arrays.stream(searchKeyWords)
                .flatMap(searchKeyWord -> invertedIndexes.getOrDefault(searchKeyWord, Collections.emptySet()).stream())
                .distinct()
                .map(data::get);
    }
}
