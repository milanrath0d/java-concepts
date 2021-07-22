package org.milan.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for testing use cases of {@link Future}
 *
 * @author Milan Rathod
 */
class FutureTest {

    private SquareCalculator squareCalculator;

    @Test
    void givenExecutorIsSingleThreaded_whenTwoExecutionsAreTriggered_thenRunInSequence() throws InterruptedException, ExecutionException {
        squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> result1 = squareCalculator.calculate(4);
        Future<Integer> result2 = squareCalculator.calculate(1000);

        while (!result1.isDone() || !result2.isDone()) {
            System.out.printf("Task 1 is %s and Task 2 is %s.%n", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done");
            Thread.sleep(300);
        }

        assertEquals(16, result1.get().intValue());
        assertEquals(1000000, result2.get().intValue());
    }

    @Test
    void whenGetWithTimeoutLowerThanExecutionTime_thenThrowException() {
        squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> result = squareCalculator.calculate(4);

        assertThrows(TimeoutException.class, () -> result.get(500, TimeUnit.MILLISECONDS));
    }

    @Test
    void givenExecutorIsMultiThreaded_whenTwoExecutionsAreTriggered_thenRunInParallel() throws InterruptedException, ExecutionException {
        squareCalculator = new SquareCalculator(Executors.newFixedThreadPool(2));

        Future<Integer> result1 = squareCalculator.calculate(4);
        Future<Integer> result2 = squareCalculator.calculate(1000);

        while (!result1.isDone() || !result2.isDone()) {
            System.out.printf("Task 1 is %s and Task 2 is %s.%n", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done");
            Thread.sleep(300);
        }

        assertEquals(16, result1.get().intValue());
        assertEquals(1000000, result2.get().intValue());
    }

    @Test
    void whenCancelFutureAndCallGet_thenThrowException() {
        squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> result = squareCalculator.calculate(4);

        boolean canceled = result.cancel(true);

        assertTrue(canceled, "Future was canceled");
        assertTrue(result.isCancelled(), "Future was canceled");

        assertThrows(CancellationException.class, result::get);
    }

}