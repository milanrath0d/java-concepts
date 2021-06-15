package org.milan.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BinaryNumber}
 *
 * @author Milan Rathod
 */
class BinaryNumberTest {

    private BinaryNumber binaryNumber;

    @BeforeEach
    void setup() {
        binaryNumber = new BinaryNumber();
    }

    @Test
    public void given_binaryLiteral_thenReturnDecimalValue() {
        byte five = 0b101;
        assertEquals((byte) 5, five);

        short three = 0b11;
        assertEquals((short) 3, three);

        int nine = 0B1001;
        assertEquals(9, nine);

        long twentyNine = 0B11101;
        assertEquals(29, twentyNine);

        int minusThirtySeven = -0B100101;
        assertEquals(-37, minusThirtySeven);
    }

    @Test
    void decimalToBinary() {
        assertEquals(1000, binaryNumber.decimalToBinary(8));
        assertEquals(10100, binaryNumber.decimalToBinary(20));
    }

    @Test
    void decimalToBinaryV2() {
        assertEquals(1000, binaryNumber.decimalToBinaryV2(8));
        assertEquals(10100, binaryNumber.decimalToBinaryV2(20));
    }

    @Test
    void binaryToDecimal() {
        assertEquals(8, binaryNumber.binaryToDecimal(1000));
        assertEquals(20, binaryNumber.binaryToDecimal(10100));
    }

    @Test
    void binaryToDecimalV2() {
        assertEquals(8, binaryNumber.binaryToDecimalV2(1000));
        assertEquals(20, binaryNumber.binaryToDecimalV2(10100));
    }
}