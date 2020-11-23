package search.engine;

public class SimpleSearchEngine {
    public static int findIndex(String[] words, String searchKey) {
        int index = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(searchKey)) {
                index = i + 1;
            }
        }
        return index;
    }
}
