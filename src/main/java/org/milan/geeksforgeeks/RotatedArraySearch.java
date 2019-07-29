package org.milan.geeksforgeeks;

import java.util.Arrays;

/**
 * Search a key in a rotated array
 * <p>
 * Time Complexity - O(logn)
 * <p>
 * {@link @https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/}
 *
 * @author Milan Rathod
 */
public class RotatedArraySearch {

    /**
     * @param inputArray given array
     * @param key        key to be searched
     * @return index if present or -1 if not present
     */
    public int rotatedArraySearch(int[] inputArray, int key) {

        // check if given array is null or empty
        if (inputArray == null || inputArray.length == 0) {
            return -1;
        }

        // check if given array has only one element
        if (inputArray.length == 1) {
            return inputArray[0] == key ? 0 : -1;
        }

        int pivot = findPivot(inputArray, 0, inputArray.length - 1);

        // array is not rotated itself
        if (pivot == -1) {
            return Arrays.binarySearch(inputArray, 0, inputArray.length, key);
        }

        if (inputArray[pivot] == key) {
            return pivot;
        } else if (inputArray[0] > key) {
            return Arrays.binarySearch(inputArray, pivot + 1, inputArray.length, key);
        } else {
            return Arrays.binarySearch(inputArray, 0, pivot, key);
        }
    }

    /**
     * find pivot in the given array
     */
    private int findPivot(int[] inputArray, int low, int high) {

        // base case 1
        if (low > high) return -1;

        // base case 2
        if (low == high) return low;


        int mid = (low + high) / 2;

        if (mid < high && inputArray[mid] > inputArray[mid + 1]) return mid;

        if (mid > low && inputArray[mid] < inputArray[mid - 1]) return mid - 1;

        if (inputArray[low] >= inputArray[mid]) return findPivot(inputArray, low, mid - 1);

        return findPivot(inputArray, mid + 1, high);
    }

    /**
     * Improved solution of rotated search
     */
    public int rotatedArraySearch(int[] inputArray, int low, int high, int key) {

        // check if given array is null or empty
        if (inputArray == null || inputArray.length == 0) {
            return -1;
        }

        // check if given array has only one element
        if (inputArray.length == 1) {
            return inputArray[0] == key ? 0 : -1;
        }

        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (inputArray[mid] == key) {
            return mid;
        }

        // if inputArray[low...mid] is sorted
        if (inputArray[low] <= inputArray[mid]) {

            if (key >= inputArray[low] && key <= inputArray[mid]) {
                return rotatedArraySearch(inputArray, low, mid - 1, key);
            }
            return rotatedArraySearch(inputArray, mid + 1, high, key);
        }

        // inputArray[low...mid] is not sorted. it means inputArray[mid...high] is sorted.
        if (inputArray[mid] <= key && inputArray[high] >= key) {
            return rotatedArraySearch(inputArray, mid + 1, high, key);
        }

        return rotatedArraySearch(inputArray, low, mid - 1, key);

    }
}