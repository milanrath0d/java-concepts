package org.milan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for comparing Integer objects
 * <p>
 * {@link Integer#valueOf(int)} method cache values between -127 to 128
 * so equal operator will return true for those values when Integer object is created
 * but for other values it will return false while doing comparison using equal operator
 *
 * @author Milan Rathod
 */
class IntegerComparisonTest {

    @Test
    void compareUsingOperator() {
        Integer source = 100;
        Integer target = 100;

        assertSame(source, target);

        source = 129;
        target = 129;

        assertNotSame(source, target);
    }

    @Test
    void compareUsingOperator_valueOf() {
        Integer source = Integer.valueOf(100);
        Integer target = Integer.valueOf(100);

        assertSame(source, target);

        source = Integer.valueOf(129);
        target = Integer.valueOf(129);

        assertNotSame(source, target);
    }

    @Test
    void compareUsingEquals() {
        Integer source = 100;
        Integer target = 100;

        assertTrue(source.equals(target));

        source = 160;
        target = 160;

        assertTrue(source.equals(target));
    }

}