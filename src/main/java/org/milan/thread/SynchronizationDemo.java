package org.milan.thread;

import lombok.Data;

/**
 * Examples of synchronization keyword use cases
 *
 * @author Milan Rathod
 */
@Data
public class SynchronizationDemo {

    private int sum = 0;

    private static int staticSum = 0;

    /**
     * Instance method without synchronization
     */
    public void calculate() {
        setSum(getSum() + 1);
    }

    /**
     * Instance method with synchronization
     */
    public synchronized void calculateV2() {
        setSum(getSum() + 1);
    }

    /**
     * Static method with synchronization
     */
    public static synchronized void calculateV3() {
        staticSum += 1;
    }

    /**
     * Instance method with synchronized block
     */
    public void calculateV4() {
        synchronized (this) {
            setSum(getSum() + 1);
        }
    }

    /**
     * Static method with synchronized block
     */
    public static void calculateV5() {
        synchronized (SynchronizationDemo.class) {
            staticSum += 1;
        }
    }

    public String reentrantLock(String name) {
        String test = "";
        synchronized (this) {
            System.out.println("First time acquired by " + name);
            test += "First";
            synchronized (this) {
                System.out.println("Entering again by " + name);
                test += "Second";
                synchronized (this) {
                    System.out.println("And again by " + name);
                    test += "Third";
                }
            }
        }
        return test;
    }

    /**
     * Return staticSum
     */
    public static int getStaticSum() {
        return staticSum;
    }

    /**
     * Set staticSum to 0
     */
    public static void clearStaticSum() {
        staticSum = 0;
    }
}
