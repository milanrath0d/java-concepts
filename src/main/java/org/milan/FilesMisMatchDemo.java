package org.milan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Milan Rathod
 */
public class FilesMisMatchDemo {
    public static void main(String[] args) throws IOException {
        givenIdenticalFiles_thenShouldNotFindMismatch();
        givenDifferentFiles_thenShouldFindMismatch();
    }

    private static void givenDifferentFiles_thenShouldFindMismatch() throws IOException {
        Path filePath1 = Files.createTempFile("file3", ".txt");
        Path filePath2 = Files.createTempFile("file4", ".txt");
        Files.writeString(filePath1, "Java 12 Article");
        Files.writeString(filePath2, "Java 12 Tutorial");

        long mismatch = Files.mismatch(filePath1, filePath2);
        System.out.println(mismatch);
    }

    private static void givenIdenticalFiles_thenShouldNotFindMismatch() throws IOException {
        Path filePath1 = Files.createTempFile("file1", ".txt");
        Path filePath2 = Files.createTempFile("file2", ".txt");
        Files.writeString(filePath1, "Java 12 Article");
        Files.writeString(filePath2, "Java 12 Article");

        long mismatch = Files.mismatch(filePath1, filePath2);
        System.out.println(mismatch);
    }
}
