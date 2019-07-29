package org.milan.datastructure.array;

/**
 * Problem: {@link @https://www.geeksforgeeks.org/sort-1-n-swapping-adjacent-elements/}
 *
 * @author Milan Rathod
 */
public class SortBySwapping {

    public boolean isSortPossible(int[] arr, int[] swapFlags) {

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                int start = i;
                int min = i;

                while (start < n - 1 && swapFlags[start] != 0) {
                    min = min(arr, min, start + 1);
                    start++;
                }

                if (start != i) {
                    swap(arr, i, min);
                } else {
                    return false;
                }
            }
        }

        return isSorted(arr);
    }

    private int min(int[] input, int source, int target) {
        if (input[source] < input[target]) {
            return source;
        }
        return target;
    }

    private void swap(int[] input, int source, int target) {
        int temp = input[source];
        input[source] = input[target];
        input[target] = temp;
    }

    private boolean isSorted(int[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] != i + 1) {
                return false;
            }
        }
        return true;
    }
}
