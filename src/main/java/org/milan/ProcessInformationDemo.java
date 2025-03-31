package org.milan;

/**
 * @author Milan Rathod
 */
public class ProcessInformationDemo {
    public static void main(String[] args) {
        ProcessHandle processHandle = ProcessHandle.current();
        System.out.println(processHandle.pid());

        final ProcessHandle.Info processInfo = processHandle.info();
        System.out.println(processInfo);
    }
}
