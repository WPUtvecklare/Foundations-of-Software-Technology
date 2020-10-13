/*
 * Date: 2020-09-07
 * File Name: Codestrip.java
 * Author: Adam Bergman
 */
package ab224qr_assign1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Class Description: Strips a document by removing blank lines and comments
 * @version 1.0
 * @author Adam Bergman
 */
public class Codestrip {
    private String filePath;

    /**
     * Starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        Codestrip cp = new Codestrip();

        try {
            String fileContent = cp.getFileContents();
            String code = cp.stripCommentsAndBlankLines(fileContent);

            System.out.println(code);
            System.out.println("Number of actual lines of code: " + cp.getLinesCount(code));
            System.out.println("Number of blank lines removed: " + cp.getBlankLinesCount());
            System.out.println("Number of comments removed: " + cp.getCommentsCount(fileContent));

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please restart and try again with a different path.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts the user to enter a file path and converts it into a string
     * @return the document as a string
     * @throws IOException when the path wasn't found
     */
    private String getFileContents () throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to the file: ");
        filePath = scanner.next();
        scanner.close();

        Path path = Paths.get(filePath);

        if (!Files.exists(path) || Files.isDirectory(path)) {
            throw new FileNotFoundException();
        }

        return Files.readString(path);
    }

    /**
     * Removes comments and blank lines from a string
     * @param content to strip
     * @return content without comments and blank lines
     */
    private String stripCommentsAndBlankLines(String content) {
        String noComments = content.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
        return noComments.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

    /**
     * Calculates the amount of lines in a string
     * @param str to be counted
     * @return the amount of lines
     */
    private int getLinesCount(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }

    /**
     * Counts the amount of blank lines
     * @return the amount of blank lines
     * @throws IOException when the path wasn't found
     */
    private int getBlankLinesCount() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Stream<String> emptyLines = lines.stream().filter((String line) -> line.trim().isEmpty());
        return (int)emptyLines.count();
    }

    /**
     * Counts the amount of comments in a string
     * @param content to be counted
     * @return the amount of comments
     */
    private int getCommentsCount(String content) {
        Scanner sc = new Scanner(content);
        String line;
        int commentsCount = 0;

        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.contains("/*") || line.contains("//")) {
                commentsCount++;
            }
        }
        sc.close();

        return commentsCount;
    }
}
