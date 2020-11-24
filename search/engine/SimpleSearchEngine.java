package search.engine;

import search.engine.algorithms.SimpleSearchStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleSearchEngine {

     private SimpleSearchStrategy searchStrategy;

     public void setSearchStrategy(SimpleSearchStrategy searchStrategy) {
         this.searchStrategy = searchStrategy;
     }

     public Collection<String> search(String searchKey) {
         return searchStrategy.matchData(searchKey)
                 .map(words -> String.join(" ", words))
                 .collect(Collectors.toList());
     }

}
