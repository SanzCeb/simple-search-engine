package search.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimpleSearchEngineInput {
    private final static Scanner SCANNER = new java.util.Scanner(System.in);

    public static int readMenuChoice() {
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static String[] readInputFile(String filename) {
        var recordFile = new File(filename);
        var recordBuilder = new StringBuilder();
        try (var fileStream = new Scanner(recordFile)) {
            while (fileStream.hasNextLine()) {
                recordBuilder.append(fileStream.nextLine()).append('\n');
            }
        } catch (FileNotFoundException ignored) {
            System.out.println("File not Found!");
        }
        return recordBuilder.toString().split("\n");
    }

    public static String readUserSearchKey() {
        return SCANNER.nextLine();
    }
}
