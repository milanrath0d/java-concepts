package org.milan.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Producer consumer problem
 *
 * @author Milan Rathod
 */
public class ProducerConsumerProblem {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(3);

        Thread producerThread = new Thread(new Producer(buffer), "Producer-Thread");
        Thread consumerThread = new Thread(new Consumer(buffer), "Consumer-Thread-1");

        producerThread.start();
        consumerThread.start();
        new Thread(new Consumer(buffer), "Consumer-Thread-2").start();
    }

}

/**
 * Buffer class
 */
class Buffer {

    private final Queue<Integer> queue;

    private final int size;

    public Buffer(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public void add(int item) throws InterruptedException {

        synchronized (this) {
            while (queue.size() == size) {
                wait();
            }

            queue.add(item);

            // Notifies the consumer thread
            notify();
        }
    }

    public int poll() throws InterruptedException {

        synchronized (this) {

            while (queue.size() == 0) {
                wait();
            }

            Integer item = queue.poll();

            // Notifies the producer thread
            notify();

            return item;
        }
    }

}

/**
 * Producer Thread
 */
class Producer implements Runnable {

    private final Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        int value = 0;

        try {
            while (true) {
                buffer.add(value);

                System.out.println(Thread.currentThread().getName() + ": produced = " + value);

                value++;

                // Makes working of the program easier to understand
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.err.println("Producer thread interrupted: " + ex);
        }
    }

}

/**
 * Consumer Thread
 */
class Consumer implements Runnable {

    private final Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = buffer.poll();

                System.out.println(Thread.currentThread().getName() + ": consumed = " + value);

                // Makes working of the program easier to understand
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.err.println("Consumer Thread interrupted: " + ex);
        }
    }

}