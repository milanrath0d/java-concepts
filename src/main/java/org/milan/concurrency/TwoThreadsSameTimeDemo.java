package org.milan.concurrency;

import java.time.Instant;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

/**
 * {@link @<a href="https://www.baeldung.com/java-start-two-threads-at-same-time">...</a>}
 *
 * @author Milan Rathod
 */
public class TwoThreadsSameTimeDemo {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("===============================================");
        System.out.println("        >>> Using CountDownLatch <<<<");
        System.out.println("===============================================");

        CountDownLatch countDownLatch = new CountDownLatch(1);

        WorkerWithCountDownLatch workerWithCountDownLatch1 =new WorkerWithCountDownLatch("worker1", countDownLatch);
        WorkerWithCountDownLatch workerWithCountDownLatch2 =new WorkerWithCountDownLatch("worker1", countDownLatch);
        workerWithCountDownLatch1.start();
        workerWithCountDownLatch2.start();

        Thread.sleep(10);

        System.out.println("-----------------------------------------------");
        System.out.println(" Now release the latch:");
        System.out.println("-----------------------------------------------");
        countDownLatch.countDown();

        Thread.sleep(100);

        System.out.println("\n===============================================");
        System.out.println("        >>> Using CyclicBarrier <<<<");
        System.out.println("===============================================");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        WorkerWithCyclicBarrier workerWithCyclicBarrier1 = new WorkerWithCyclicBarrier("worker1", cyclicBarrier);
        WorkerWithCyclicBarrier workerWithCyclicBarrier2 = new WorkerWithCyclicBarrier("worker1", cyclicBarrier);
        workerWithCyclicBarrier1.start();
        workerWithCyclicBarrier2.start();

        Thread.sleep(10);

        System.out.println("-----------------------------------------------");
        System.out.println(" Now open the barrier:");
        System.out.println("-----------------------------------------------");
        cyclicBarrier.await();

        Thread.sleep(100);

        System.out.println("\n===============================================");
        System.out.println("        >>> Using Phaser <<<");
        System.out.println("===============================================");

        Phaser phaser = new Phaser();
        phaser.register();

        WorkerWithPhaser workerWithPhaser1 = new WorkerWithPhaser("worker1", phaser);
        WorkerWithPhaser workerWithPhaser2 = new WorkerWithPhaser("worker1", phaser);
        workerWithPhaser1.start();
        workerWithPhaser2.start();

        Thread.sleep(10);

        System.out.println("-----------------------------------------------");
        System.out.println(" Now open the phaser barrier:");
        System.out.println("-----------------------------------------------");
        phaser.arriveAndAwaitAdvance();
    }
}

class WorkerWithCountDownLatch extends Thread {
    private final CountDownLatch countDownLatch;

    public WorkerWithCountDownLatch(String name, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        setName(name);
    }

    public void run() {
        try {
            System.out.printf("[ %s ] created, blocked by the latch...\n", getName());
            countDownLatch.await();
            System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());
        } catch (InterruptedException e) {
            // handle exception
        }
    }
}

class WorkerWithCyclicBarrier extends Thread {
    private final CyclicBarrier cyclicBarrier;

    WorkerWithCyclicBarrier(String name, CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        setName(name);
    }

    public void run() {
        try {
            System.out.printf("[ %s ] created, blocked by the barrier\n", getName());
            cyclicBarrier.await();
            System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());
        } catch (InterruptedException | BrokenBarrierException e) {
            // handle exception
        }
    }
}

class WorkerWithPhaser extends Thread {
    private final Phaser phaser;

    WorkerWithPhaser(String name, Phaser phaser) {
        this.phaser = phaser;
        phaser.register();
        setName(name);
    }

    @Override
    public void run() {
        try {
            System.out.printf("[ %s ] created, blocked by the phaser\n", getName());
            phaser.arriveAndAwaitAdvance();
            System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());
        } catch (IllegalStateException e) {
            // handle exception
        }
    }
}
