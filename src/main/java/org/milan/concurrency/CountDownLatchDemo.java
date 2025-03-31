package org.milan.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * Example for {@link java.util.concurrent.CountDownLatch}
 *
 * @author Milan Rathod
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());

        CountDownLatch countDownLatch = new CountDownLatch(5);

        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
                .limit(5)
                .toList();

        workers.forEach(Thread::start);

        countDownLatch.await();

        outputScraper.add("Latch Released");

        System.out.println(outputScraper);
    }
}

class Worker implements Runnable {

    private final List<String> outputScraper;

    private final CountDownLatch countDownLatch;

    public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        outputScraper.add("Counted Down");
        countDownLatch.countDown();
    }
}
