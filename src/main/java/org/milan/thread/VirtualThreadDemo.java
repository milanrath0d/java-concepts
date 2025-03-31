package org.milan.thread;

/**
 * @author Milan Rathod
 */
public class VirtualThreadDemo {
    public static void main(String[] args) {
        final Thread virtualThread = Thread.ofVirtual().start(() -> System.out.println("Hello World"));

        System.out.println(virtualThread.isVirtual());
        System.out.println(virtualThread.isDaemon());
    }
}
