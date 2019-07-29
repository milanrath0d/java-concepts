package org.milan.misc;

import java.util.Arrays;

/**
 * Generate All the strings of n bits
 * <p>
 * Refer {@link @https://algorithms.tutorialhorizon.com/generate-all-strings-of-n-bits/}
 *
 * @author Milan Rathod
 */
public class NbitsStrings {

    private int[] A;

    NbitsStrings(int n) {
        A = new int[n];
    }

    public static void main(String[] args) {
        new NbitsStrings(3).nBits(3);
    }

    /**
     * Time Complexity of O(2 power of n)
     */
    public void nBits(int n) {
        if (n < 1) {
            System.out.println(Arrays.toString(A));
        } else {
            A[n - 1] = 0;
            nBits(n - 1);
            A[n - 1] = 1;
            nBits(n - 1);
        }
    }
}
