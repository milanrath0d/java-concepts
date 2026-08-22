package org.milan;

import java.util.Scanner;

/**
 * @author Milan Rathod
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Total Lines. ");
        int totalLines = scanner.nextInt();

        printLoop(totalLines);
    }

    private static void printLoop(int totalLines) {
        for (int i = 1; i <= totalLines; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
