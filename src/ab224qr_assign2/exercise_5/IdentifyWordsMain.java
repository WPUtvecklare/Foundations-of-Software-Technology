/*
 * Date: 2020-09-26
 * File Name: IdentifyWordsMain.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class Description: Reads a file and writes the content to a new file keeping only alphabetical letters
 * @version 1.0
 * @author Adam Bergman
 */
public class IdentifyWordsMain {
    /**
     * The starting point of the application
     * @param args path to the file to read
     */
    public static void main(String[] args) {
        try {
            File toRead;

            if (args.length == 1) {
                toRead = new File(args[0]); // Read file from program arguments
            } else {
                toRead = getFile(); // Read file from console input
            }

            File toWrite = createFile("words.txt");

            System.out.println("Reading: " + toRead.getName());
            readAndWrite(toRead, toWrite);
            System.out.println("---\nWritten to: " + toWrite.getName());

        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path. Shutting down...");
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get file path via console input
     * @return the file
     */
    private static File getFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        String path = scanner.nextLine();
        File file = new File(path);

        while (!file.exists() || file.isDirectory()) {
            System.out.print("Invalid path. Please try again: ");
            path = scanner.nextLine();
            file = new File(path);
        }

        scanner.close();
        return file;
    }

    /**
     * Creates a file and re-creates it if it already exists
     * @param pathname the full path to the file
     * @return the file
     * @throws IOException if creating the file fails
     */
    private static File createFile(String pathname) throws IOException {
        File file = new File(pathname);
        boolean doesNotExist = file.createNewFile();

        if (!doesNotExist) {
            file.delete();
            file.createNewFile();
        }

        return file;
    }

    /**
     * Reads a file line-by-line, strips the content by only keeping
     * alphabetical characters and spaces and writes the filtered
     * content to the given file to write
     * @param toRead the file to read
     * @param toWrite the file to write
     * @throws Exception if file was not found or if it cannot be written to
     */
    private static void readAndWrite(File toRead, File toWrite) throws Exception {
        Scanner scanner = new Scanner(toRead);
        FileWriter fileWriter = new FileWriter(toWrite);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String filtered = line
                    .replaceAll("-", " ")
                    .replaceAll("[^\\p{Alpha}\\p{Space}]", "")
                    .replaceAll("  ", " ");

            fileWriter.write(filtered);
            fileWriter.write(System.lineSeparator());
        }

        fileWriter.flush();
        fileWriter.close();
    }
}
