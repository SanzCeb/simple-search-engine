package search.client;

import search.engine.SimpleSearchEngine;

public class SimpleSearchEngineCLI {
    public static void run() {
        var scanner = new java.util.Scanner(System.in);

        System.out.println("Enter the number of people:");
        var numRecords = scanner.nextInt();
        scanner.nextLine();

        var records = new String [numRecords];

        System.out.println("Enter all people:");
        for (int i = 0; i < numRecords; i++) {
            records[i] = scanner.nextLine();
        }

        System.out.println("Enter the number of search queries:");
        int numQueries = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numQueries; i++) {
            System.out.println("Enter data to search people:");
            var searchKey = scanner.nextLine().toLowerCase();
            var foundRecords = SimpleSearchEngine.findCoincidences(records, searchKey);
            if (foundRecords.length > 0) {
                System.out.println("Found people:");
                for (var foundRecord : foundRecords) {
                    System.out.println(foundRecord);
                }
            } else {
                System.out.println("No matching people found.");
            }
        }

    }

}
