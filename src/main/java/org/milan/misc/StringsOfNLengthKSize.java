package org.milan.misc;

import java.util.Arrays;

/**
 * Refer {@link @https://algorithms.tutorialhorizon.com/generate-all-the-strings-of-length-n-from-0-to-k-1/}
 *
 * @author Milan Rathod
 */
public class StringsOfNLengthKSize {

    int[] arr;

    StringsOfNLengthKSize(int n) {
        arr = new int[n];
    }

    public void generate(int n, int k) {
        if (n < 1) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = 0; i < k; i++) {
                arr[n - 1] = i;
                generate(n - 1, k);
            }
        }
    }

    public static void main(String[] args) {
        new StringsOfNLengthKSize(2).generate(2, 3);
    }
}
