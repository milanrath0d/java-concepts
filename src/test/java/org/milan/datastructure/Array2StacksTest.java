package org.milan.datastructure;

import org.junit.Test;

/**
 * Test Class for {@link Array2Stacks}
 *
 * @author Milan Rathod
 */
public class Array2StacksTest {

    @Test
    public void test() {
        Array2Stacks<Integer> a2s = new Array2Stacks<>(Integer.class, 10);
        a2s.push(1, 5);
        a2s.pop(1);
        a2s.pop(2);
    }
}