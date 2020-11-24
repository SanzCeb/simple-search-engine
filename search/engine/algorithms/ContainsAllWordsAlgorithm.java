package search.engine.algorithms;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ContainsAllWordsAlgorithm  extends SimpleSearchStrategy {

    public ContainsAllWordsAlgorithm(List<String> data) {
        super(data);
    }

    @Override
    public Stream<String[]> matchData(String searchKey) {

        var searchKeyWords = searchKey.split(" ");
        if (!invertedIndexes.containsKey(searchKey)) {
            for (int i = 0; i < data.size(); i++) {
                var containsAllWords = true;
                for (var searchKeyWord : searchKeyWords) {
                    if (SimpleLinearSearch.notContainsWord(data.get(i), searchKeyWord)) {
                        containsAllWords = false;
                        break;
                    }
                }
                if (containsAllWords) {
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
