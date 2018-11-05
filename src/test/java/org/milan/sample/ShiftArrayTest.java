package org.milan.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test Class for {@link ShiftArray}
 *
 * @author Milan Rathod
 */
public class ShiftArrayTest {

    @Test
    public void computeFinalArray() {
        int[] inputArray = {12, 1, 13, 24, 12, 66, 12};
        int[] expectedResult = {1, 1, 1, 1, 13, 24, 66};

        ShiftArray shiftArray = new ShiftArray();
        int[] result = shiftArray.computeFinalArray(inputArray, 12);

        Assert.assertTrue(Arrays.equals(result, expectedResult));

    }
}