package org.milan;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Example of getPath, getAbsolutePath and getCanonicalPath methods to get file path
 *
 * @author Milan Rathod
 */
public class FilePathTest {

    private static String userDir;

    @BeforeAll
    static void createFiles() throws IOException {
        userDir = System.getProperty("user.dir");

        new File(userDir + "/milan/foo").mkdirs();
        new File(userDir + "/milan/bar/baz").mkdirs();
        new File(userDir + "/milan/foo/foo-one.txt").createNewFile();
        new File(userDir + "/milan/foo/foo-two.txt").createNewFile();
        new File(userDir + "/milan/bar/bar-one.txt").createNewFile();
        new File(userDir + "/milan/bar/bar-two.txt").createNewFile();
        new File(userDir + "/milan/bar/baz/baz-one.txt").createNewFile();
        new File(userDir + "/milan/bar/baz/baz-two.txt").createNewFile();
    }

    @AfterAll
    static void deleteFiles() {
        deleteRecursively(new File(userDir + "/milan"));
    }

    @Test
    public void whenPathResolved_thenSuccess() {
        File file = new File("baeldung/foo/foo-one.txt");
        String expectedPath = isWindows() ? "baeldung\\foo\\foo-one.txt" : "baeldung/foo/foo-one.txt";
        String actualPath = file.getPath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void whenAbsolutePathResolved_thenSuccess() {
        File file = new File("baeldung/foo/foo-one.txt");
        String expectedPath = isWindows() ? userDir + "\\baeldung\\foo\\foo-one.txt" : userDir + "/baeldung/foo/foo-one.txt";
        String actualPath = file.getAbsolutePath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void whenAbsolutePathWithShorthandResolved_thenSuccess() {
        File file = new File("baeldung/bar/baz/../bar-one.txt");
        String expectedPath = isWindows() ? userDir + "\\baeldung\\bar\\baz\\..\\bar-one.txt" : userDir + "/baeldung/bar/baz/../bar-one.txt";
        String actualPath = file.getAbsolutePath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void whenCanonicalPathWithShorthandResolved_thenSuccess() throws IOException {
        File file = new File("baeldung/bar/baz/../bar-one.txt");
        String expectedPath = isWindows() ? userDir + "\\baeldung\\bar\\bar-one.txt" : userDir + "/baeldung/bar/bar-one.txt";
        String actualPath = file.getCanonicalPath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void whenCanonicalPathWithDotShorthandResolved_thenSuccess() throws IOException {
        File file = new File("baeldung/bar/baz/./baz-one.txt");
        String expectedPath = isWindows() ? userDir + "\\baeldung\\bar\\baz\\baz-one.txt" : userDir + "/baeldung/bar/baz/baz-one.txt";
        String actualPath = file.getCanonicalPath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void givenWindowsOs_whenCanonicalPathWithWildcard_thenIOException() {
        assertThrows(IOException.class, () -> new File("*").getCanonicalPath());
    }

    private static boolean isWindows() {
        String osName = System.getProperty("os.name");
        return osName.contains("Windows");
    }

    private static void deleteRecursively(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                deleteRecursively(file);
            }
            file.delete();
        }
        dir.delete();
    }

}
