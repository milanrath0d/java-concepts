package org.milan.datastructure;

/**
 * Problem: Tower of hanoi problem
 *
 * @author Milan Rathod
 */
public class TowersOfHanoi {

    private static final String SOURCE_PEG = "A";

    private static final String AUXILIARY_PEG = "B";

    private static final String TARGET_PEG = "C";

    // Time Complexity O(2 power of n)
    public void listSteps(int n, String source, String target, String aux) {
        if (n == 1) {
            System.out.println("Please move from Peg " + source + "\tTo Peg\t"
                    + target);
        } else {

            // Move top n - 1 disks from A to B using C as auxiliary
            listSteps(n - 1, source, aux, target);

            // Move remaining disks from A to C
            listSteps(1, source, target, aux);

            // Move n -1 disks from B to C using A as auxiliary
            listSteps(n - 1, aux, target, source);
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        new TowersOfHanoi().listSteps(4, SOURCE_PEG, TARGET_PEG, AUXILIARY_PEG);

        long endTime = System.currentTimeMillis();

        System.out.println("Done in " + (endTime - startTime) / 1000
                + "\t seconds");
    }
}
