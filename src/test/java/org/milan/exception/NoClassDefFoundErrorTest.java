package org.milan.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing {@link NoClassDefFoundError}
 *
 * @author Milan Rathod
 */
public class NoClassDefFoundErrorTest {

    @Test
    public void givenInitErrorInClass_whenLoadClass_thenNoClassDefFoundError() {
        Assertions.assertThrows(NoClassDefFoundError.class, this::getClassWithInitErrors);
    }

    public ClassWithInitErrors getClassWithInitErrors() {
        ClassWithInitErrors test;
        try {
            test = new ClassWithInitErrors();
        } catch (Throwable t) {
            System.out.println(t);
        }
        test = new ClassWithInitErrors();
        return test;
    }
}

class ClassWithInitErrors {
    static int data = 1 / 0;
}
