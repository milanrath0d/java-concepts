package org.milan.geeksforgeeks;

/**
 * Problem: Splitwise simplify debt
 * <p>
 * {@link @https://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-borrowed-money/}
 * <p>
 * Time Complexity : O(N*N)
 * <p>
 * where N is number of persons
 *
 * @author Milan Rathod
 */
public class Splitwise {

    public static void main(String[] args) {

        // Input Graph describing debts of persons to each other
        int[][] input = {{0, 1000, 2000},
                {0, 0, 5000},
                {0, 0, 0}};

        new Splitwise().simplifyDebt(input, 3);
    }

    public void simplifyDebt(int[][] input, int persons) {

        // Amount array to hold amount(i.e. credit/debit) of each person
        int[] amount = new int[persons];

        for (int i = 0; i < persons; i++) {
            for (int j = 0; j < persons; j++) {
                amount[i] += (input[j][i] - input[i][j]);
            }
        }

        minimizeCashFlow(amount);

    }

    private void minimizeCashFlow(int[] amount) {

        int maxCredit = getMax(amount);

        int maxDebit = getMin(amount);

        // Return if maxCredit and maxDebit is 0
        if (amount[maxCredit] == 0 && amount[maxDebit] == 0)
            return;

        int min = minOf2(-amount[maxDebit], amount[maxCredit]);
        amount[maxCredit] -= min;
        amount[maxDebit] += min;

        System.out.println("Person " + maxDebit + " pays " + min + " to Person " + maxCredit);

        minimizeCashFlow(amount);
    }

    /**
     * Get minimum out of two given values
     */
    private int minOf2(int x, int y) {
        return (x < y) ? x : y;
    }

    /**
     * Get index of a maximum element
     */
    private int getMax(int[] amount) {

        int max = 0;
        for (int i = 1; i < amount.length; i++) {
            if (amount[i] > amount[max]) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Get index of a minimum element
     */
    private int getMin(int[] amount) {

        int min = 0;
        for (int i = 1; i < amount.length; i++) {
            if (amount[i] < amount[min]) {
                min = i;
            }
        }
        return min;
    }
}
