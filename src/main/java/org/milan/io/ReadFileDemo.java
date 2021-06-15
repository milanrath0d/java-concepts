package org.milan.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Different Ways of Reading file
 *
 * @author Milan Rathod
 */
public class ReadFileDemo {

    private static final String FILE_PATH = "C:" + File.separator + "test" + File.separator + "readFile.txt";

    public static void main(String[] args) throws Exception {

        // Read file using FileReader
        readUsingFileReader(FILE_PATH);

        // Read file using BufferedReader
        readUsingBufferedReader(FILE_PATH);

        // Read file using Scanner
        readUsingScanner(FILE_PATH);

        // Read file using Java 7 apis
        readUsingJava7(FILE_PATH);

        // Read file using Java 8 apis
        readUsingJava8(FILE_PATH);
    }

    private static void readUsingFileReader(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        char[] buffer = new char[128];
        int numberOfChars = fileReader.read(buffer);
        while (numberOfChars != -1) {
            System.out.println(String.valueOf(buffer, 0, numberOfChars));
            numberOfChars = fileReader.read(buffer);
        }
        fileReader.close();
    }

    /**
     * Similar to FileReader but since it buffered input from files into memory for efficient read
     * So better than FileReader since memory read operation is faster than file read operation.
     */
    private static void readUsingBufferedReader(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        char[] buffer = new char[256];
        int numberOfChars = bufferedReader.read(buffer);
        while (numberOfChars != -1) {
            System.out.println(String.valueOf(buffer, 0, numberOfChars));
            numberOfChars = bufferedReader.read(buffer);
        }
        bufferedReader.close();
    }

    private static void readUsingScanner(String file) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(file));
        while (sc.hasNext()) {
            String str = sc.nextLine();
            System.out.println(str);
        }
        sc.close();
    }

    private static void readUsingJava8(String file) throws IOException {
        Files.lines(Paths.get(file)).forEach(System.out::println);
    }

    private static void readUsingJava7(String file) throws IOException {
        System.out.println(new String(Files.readAllBytes(Paths.get(file))));
    }
}