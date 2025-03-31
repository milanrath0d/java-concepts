package org.milan.thread;

/**
 * Example of thread deadlock
 *
 * @author Milan Rathod
 */
public class ThreadDeadlock {

    public static void main(String[] args) {
        new ThreadDeadlock().testDeadlock();
    }

    public void testDeadlock() {
        Object o1 = new Object();
        Object o2 = new Object();

        Thread t1 = new Thread(new SyncThread(o1, o2), "Thread-1");
        Thread t2 = new Thread(new SyncThread(o2, o1), "Thread-2");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

    static class SyncThread implements Runnable {

        private final Object obj1;

        private final Object obj2;

        public SyncThread(Object obj1, Object obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " acquiring lock on " + obj1);
            synchronized (obj1) {
                System.out.println(name + " acquired lock on " + obj1);
                work();
                System.out.println(name + " acquiring lock on " + obj2);
                synchronized (obj2) {
                    System.out.println(name + " acquired lock on " + obj2);
                    work();
                }
                System.out.println(name + " released lock on " + obj2);
            }
            System.out.println(name + " released lock on " + obj1);
            System.out.println(name + " finished execution.");

        }

        private void work() {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
