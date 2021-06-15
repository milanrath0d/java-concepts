package org.milan.thread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for {@link SynchronizationDemo}
 *
 * @author Milan Rathod
 */
class SynchronizationDemoTest {

    @BeforeEach
    void setup() {
        SynchronizationDemo.clearStaticSum();
    }

    @Test
    void testWithoutSynchronization() throws InterruptedException {
        SynchronizationDemo synchronizationDemo = new SynchronizationDemo();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000)
                .forEach(value -> executorService.submit(synchronizationDemo::calculate));
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertNotEquals(1000, synchronizationDemo.getSum());
    }

    @Test
    void testWithInstanceMethodSynchronization() throws InterruptedException {
        SynchronizationDemo synchronizationDemo = new SynchronizationDemo();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000)
                .forEach(value -> executorService.submit(synchronizationDemo::calculateV2));
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, synchronizationDemo.getSum());
    }

    @Test
    void testWithStaticMethodSynchronization() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000)
                .forEach(value -> executorService.submit(SynchronizationDemo::calculateV3));
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, SynchronizationDemo.getStaticSum());
    }

    @Test
    void testWithInstanceSynchronizationBlock() throws InterruptedException {
        SynchronizationDemo synchronizationDemo = new SynchronizationDemo();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000)
                .forEach(value -> executorService.submit(synchronizationDemo::calculateV4));
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, synchronizationDemo.getSum());
    }

    @Test
    void testWithStaticSynchronizationBlock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 1000)
                .forEach(value -> executorService.submit(SynchronizationDemo::calculateV5));
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, SynchronizationDemo.getStaticSum());
    }

    @Test
    void testReentrantLock() throws InterruptedException {
        SynchronizationDemo synchronizationDemo = new SynchronizationDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        StringBuilder stringBuilder = new StringBuilder();
        Stream.of("first", "second")
                .forEach(value -> executorService.submit(() -> {
                    stringBuilder.append(synchronizationDemo.reentrantLock(value));
                }));
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals("FirstSecondThirdFirstSecondThird", stringBuilder.toString());
    }

}