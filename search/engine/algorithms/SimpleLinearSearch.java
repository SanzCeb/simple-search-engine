package search.engine.algorithms;

class SimpleLinearSearch {

    static boolean containsWord (String[] records, String searchKey) {
        for (String record : records) {
            if (record.equalsIgnoreCase(searchKey)) {
                return true;
            }
        }
        return false;
    }

    static boolean notContainsWord (String[] records, String searchKey) {
        return ! containsWord(records, searchKey);
    }
}
