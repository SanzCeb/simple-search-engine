package search.engine;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSearchEngine {
    public static int findIndex(String[] records, String searchKey) {
        int index = -1;
        var lowerCasedSearchKey = searchKey.toLowerCase();

        for (int i = 0; i < records.length; i++) {
            var record = records[i].toLowerCase();
            if (record.contains(lowerCasedSearchKey)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public static String[] findCoincidences(String[] records, String searchKey) {
        var foundRecords = new String[records.length];

        for (int i = 0, j = 0; i < records.length; i++) {
            var index = findIndex(new String[]{records[i]}, searchKey);
            if (index != - 1) {
                foundRecords[j] = records[i];
                j++;
            }
        }

        return Arrays.stream(foundRecords).filter(Objects::nonNull).toArray(String[]::new);
    }
}
