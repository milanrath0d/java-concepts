package org.milan.interfaces;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Milan Rathod
 */
class FunctionalInterfaceDemoTest {

    @Test
    void functionInterfaceApplyMethodTestForClassTestA() {
        Functional<String, Integer> functionalA = new TestA();

        Integer result = functionalA.toFunction().apply("test");

        assertEquals(4, result);
    }

    @Test
    void functionInterfaceApplyMethodTestForClassTestB() {
        Functional<String, Integer> functionalB = new TestB();

        Integer result = functionalB.toFunction().apply("test");

        assertEquals(3556498, result);
    }

    @Test
    void functionInterfaceComposeMethod() {
        Functional<String, Integer> functionalA = new TestA();
        Function<Integer, String> quote = s -> "'" + s + "'";

        Function<String, String> result = quote.compose(functionalA.toFunction());

        assertEquals("'4'", result.apply("test"));
    }

    @Test
    void functionInterfaceAndThenMethod() {
        Functional<String, Integer> functionalA = new TestA();
        Function<Integer, String> quote = s -> "'" + s + "'";

        Function<String, String> result = functionalA.toFunction().andThen(quote);

        assertEquals("'4'", result.apply("test"));
    }
}
