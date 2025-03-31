package org.milan;

/**
 * @author Milan Rathod
 */
public class NullPointerLogImprovementDemo {
    public static void main(String[] args) {
        npeLogImprovement();
    }

    private static void npeLogImprovement() {
        String text = null;
        text.toString();

        int[] arr = null;
        arr[0] = 1;
    }
}
