package org.milan.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Milan Rathod
 */
class ShortToByteTest {

    @Test
    void shortToByteFunctionTest() {
        ShortToByte shortToByte = new ShortToByte();

        byte result = shortToByte.transform((short) 1, s -> (byte) (s * 2));

        assertEquals((byte) 2, result);
    }
}
