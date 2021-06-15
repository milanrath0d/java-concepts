package org.milan.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@link java.util.concurrent.BlockingQueue} usage for producer-consumer problem
 *
 * @author Milan Rathod
 */
public class BlockingQueueDemo {

    private static final int BOUND = 10;

    private static final int NO_OF_PRODUCERS = 4;

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(BOUND);

        int no_of_consumers = Runtime.getRuntime().availableProcessors();

        int poisonPill = Integer.MAX_VALUE;

        int poisonPillPerProducer = no_of_consumers / NO_OF_PRODUCERS;

        int mod = no_of_consumers % NO_OF_PRODUCERS;

        // Start all producers thread
        for (int i = 1; i < NO_OF_PRODUCERS; i++) {
            new Thread(new NumbersProducer(blockingQueue, poisonPill, poisonPillPerProducer)).start();
        }

        // Start all consumers thread
        for (int i = 0; i < no_of_consumers; i++) {
            new Thread(new NumbersConsumer(blockingQueue, poisonPill)).start();
        }

        new Thread(new NumbersProducer(blockingQueue, poisonPill, poisonPillPerProducer + mod)).start();
    }
}

class NumbersProducer implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    private final int poisonPill;

    private final int poisonPillPerProducer;

    NumbersProducer(BlockingQueue<Integer> blockingQueue, int poisonPill, int poisonPillPerProducer) {
        this.blockingQueue = blockingQueue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            System.out.println("Producer thread " + Thread.currentThread().getName() + " is interrupted");
        }
    }

    private void generateNumbers() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            blockingQueue.put(ThreadLocalRandom.current().nextInt(100));
        }

        for (int i = 0; i < poisonPillPerProducer; i++) {
            blockingQueue.put(poisonPill);
        }
    }
}

class NumbersConsumer implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    private final int poisonPill;

    NumbersConsumer(BlockingQueue<Integer> blockingQueue, int poisonPill) {
        this.blockingQueue = blockingQueue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer number = blockingQueue.take();

                if (number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);

            } catch (InterruptedException e) {
                System.out.println("Consumer thread " + Thread.currentThread().getName() + " is interrupted");
            }
        }
    }
}
