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
                for (String searchKeyWord : searchKeyWords) {
                    if (SimpleLinearSearch.containsWord(data.get(i), searchKeyWord)) {
                        notContainsAnyWord = false;
                        break;
                    }
                }
                if (notContainsAnyWord) {
                    addInvertedIndex(searchKey, i);
                }
            }
        }

        return invertedIndexes
                .getOrDefault(searchKey, Collections.emptySet())
                .stream()
                .map(data::get);
    }

}
