package search.client;

import search.engine.SimpleSearchEngine;

public class SimpleSearchEngineCLI {
    public static void run() {
        var scanner = new java.util.Scanner(System.in);
        var words = scanner.nextLine().split(" ");
        var searchKey = scanner.nextLine();

        int index = SimpleSearchEngine.findIndex(words, searchKey);

        var output = (index == -1) ? "Not found" : String.valueOf(index);

        System.out.println(output);

    }
}
