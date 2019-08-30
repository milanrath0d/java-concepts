package org.milan.datastructure.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Class for {@link InfixToPostfix}
 *
 * @author Milan Rathod
 */
public class InfixToPostfixTest {

    @Test
    public void testConversion() {
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        String result = infixToPostfix.conversion("a+b*c+d");

        Assert.assertEquals(result, "abc*+d+");
    }
}