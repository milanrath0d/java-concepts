package org.milan.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing {@link ClassNotFoundException}
 *
 * @author Milan Rathod
 */
public class ClassNotFoundExceptionTest {

    @Test
    public void givenNoDriversInClassPath_whenLoadDrivers_thenClassNotFoundException() {
        Assertions.assertThrows(ClassNotFoundException.class, () -> Class.forName("oracle.jdbc.driver.OracleDriver"));
    }
}
