package search.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleSearchEngineInput {
    private final static Scanner SCANNER = new java.util.Scanner(System.in);

    public static int readMenuChoice() {
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<String> readInputFile(String filename) {
        var recordFile = new File(filename);
        List<String> recordBuilder = new ArrayList<>();
        try (var fileStream = new Scanner(recordFile)) {
            while (fileStream.hasNextLine()) {
                recordBuilder.add(fileStream.nextLine());
            }
        } catch (FileNotFoundException ignored) {
            System.out.println("File not Found!");
        }
        return recordBuilder;
    }

    public static String readUserSearchKey() {
        return SCANNER.nextLine();
    }

    public static String readUserSearchStrategy() {
        return SCANNER.nextLine();
    }
}
