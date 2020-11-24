package search.client;

import search.engine.SimpleSearchEngine;
import search.engine.algorithms.ContainsAllWordsAlgorithm;
import search.engine.algorithms.ContainsAtLeastOneWordAlgorithm;
import search.engine.algorithms.NotContainsAnyWordAlgorithm;

import java.util.Collection;
import java.util.List;

public class SimpleSearchEngineCLI {
    private static List<String> RECORDS;
    private static SimpleSearchEngine SEARCH_ENGINE;

    public static void run(String recordFileName) {
        RECORDS = SimpleSearchEngineInput.readInputFile(recordFileName);
        SEARCH_ENGINE = new SimpleSearchEngine();

        int menuChoice;
        do {
            printMenu();
            menuChoice = SimpleSearchEngineInput.readMenuChoice();
            runMenuChoice(menuChoice);
        } while (menuChoice != 0);

    }

    private static void runMenuChoice(int menuChoice) {
        switch (menuChoice) {
            case 0:
                runExit();
                break;
            case 1:
                runFindPerson();
                break;
            case 2:
                runPrintAllPeople();
                break;
            default:
                System.out.println("Incorrect option! Try again.");
                break;
        }
    }

    private static void runPrintAllPeople() {
        System.out.println("=== List of people ===");
        printRecords(RECORDS);
    }

    private static void runFindPerson() {
        setSearchEngineStrategy();
        System.out.println("Enter a name or email to search all suitable people:");
        var searchKey = SimpleSearchEngineInput.readUserSearchKey();
        var foundRecords = SEARCH_ENGINE.search(searchKey);
        if (foundRecords.size() > 0) {
            System.out.printf("%d persons found:%n", foundRecords.size());
            printRecords(foundRecords);
        } else {
            System.out.println("No matching people found.");
        }
    }

    private static void setSearchEngineStrategy() {
        boolean wrongStrategy;
        do {
            wrongStrategy = false;
            System.out.println("Select a matching strategy: ALL, ANY, NONE");
            String searchStrategy = SimpleSearchEngineInput.readUserSearchStrategy();
            switch (searchStrategy.toUpperCase()) {
                case "ALL":
                    SEARCH_ENGINE.setSearchStrategy(new ContainsAllWordsAlgorithm(RECORDS));
                    break;
                case "ANY":
                    SEARCH_ENGINE.setSearchStrategy(new ContainsAtLeastOneWordAlgorithm(RECORDS));
                    break;
                case "NONE":
                    SEARCH_ENGINE.setSearchStrategy(new NotContainsAnyWordAlgorithm(RECORDS));
                    break;
                default:
                    System.out.println("Please, select a valid strategy!");
                    wrongStrategy = true;
                    break;
            }
        } while (wrongStrategy);
    }

    private static void runExit() {
        System.out.println("Bye!");
    }

    private static void printMenu() {
        var menu = "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit";

        System.out.println(menu);
    }

    private static void printRecords(Collection<?> records) {
        records.forEach(System.out::println);
    }

}
