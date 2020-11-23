package search.client;

import search.engine.SimpleSearchEngine;

import java.util.Arrays;
import java.util.Scanner;

public class SimpleSearchEngineCLI {
    private final static Scanner SCANNER = new java.util.Scanner(System.in);
    private static String[] RECORDS;

    public static void run() {
        var numRecords = getNumberOfRecords();
        RECORDS = recordPeople(numRecords);
        int menuChoice;

        do {
            printMenu();
            menuChoice = readMenuChoice();
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
        } while (menuChoice != 0);

    }

    private static int readMenuChoice() {
        return Integer.parseInt(SCANNER.nextLine());
    }

    private static void runPrintAllPeople() {
        System.out.println("=== List of people ===");
        printRecords(RECORDS);
    }

    private static void runFindPerson() {
        System.out.println("Enter data to search people:");
        var searchKey = SCANNER.nextLine().toLowerCase();
        var foundRecords = SimpleSearchEngine.findCoincidences(RECORDS, searchKey);
        if (foundRecords.length > 0) {
            System.out.println("Found people:");
            printRecords(foundRecords);
        } else {
            System.out.println("No matching people found.");
        }
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

    private static String[] recordPeople(int numRecords) {
        var records = new String[numRecords];
        System.out.println("Enter all people:");
        for (int i = 0; i < numRecords; i++) {
            records[i] = SCANNER.nextLine();
        }
        return  records;
    }

    private static int getNumberOfRecords() {
        System.out.println("Enter the number of people:");
        var numRecords = SCANNER.nextInt();
        SCANNER.nextLine();
        return numRecords;
    }

    private static void printRecords(String[] records) {
        Arrays.stream(records).forEach(System.out::println);
    }

}
