package org.milan.misc;

/**
 * Work with binary numbers in java
 *
 * @author Milan Rathod
 */
public class BinaryNumber {

    public int decimalToBinary(int number) {
        return Integer.parseInt(Integer.toBinaryString(number));
    }

    public int decimalToBinaryV2(int number) {

        // Base condition
        if (number == 0) {
            return number;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int quotient = number;

        while (quotient > 0) {
            int remainder = quotient % 2;
            stringBuilder.append(remainder);
            quotient /= 2;
        }

        return Integer.parseInt(stringBuilder.reverse().toString());
    }

    public int binaryToDecimal(int number) {
        return Integer.valueOf(String.valueOf(number), 2);
    }

    public int binaryToDecimalV2(int number) {
        int decimalNumber = 0;
        int base = 1;

        while (number > 0) {
            int lastDigit = number % 10;
            number /= 10;
            decimalNumber += lastDigit * base;
            base *= 2;
        }

        return decimalNumber;
    }
}
