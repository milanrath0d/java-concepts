package org.milan.timer;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Test class for {@link java.util.Timer} and {@link java.util.TimerTask}
 *
 * @author Milan Rathod
 */
public class TimerTest {

    @Test
    void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() throws InterruptedException {
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on: " + new Date() + "\n" + "Thread's name: " + Thread.currentThread()
                        .getName());
            }
        };

        final Timer timer = new Timer("Timer");

        final long delay = 1000L;

        timer.schedule(timerTask, delay);

        Thread.sleep(delay * 2);

        timer.cancel();
    }

    @Test
    public void givenUsingTimer_whenSchedulingRepeatedTask_thenCorrect() throws InterruptedException {
        final TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        final Timer timer = new Timer("Timer");

        final long delay = 1000L;
        final long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

        Thread.sleep(delay * 2);
        timer.cancel();
    }

    @Test
    public void givenUsingTimer_whenSchedulingDailyTask_thenCorrect() throws InterruptedException {
        final TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        final Timer timer = new Timer("Timer");

        final long delay = 1000L;
        final long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

        Thread.sleep(delay * 2);
        timer.cancel();
    }

    @Test
    public void givenUsingTimer_whenCancelingTimerTask_thenCorrect() throws InterruptedException {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on " + new Date());
                cancel();
            }
        };
        final Timer timer = new Timer("Timer");

        final long delay = 1000L;
        final long period = 1000L;
        timer.scheduleAtFixedRate(task, delay, period);

        Thread.sleep(delay * 3);
    }

    @Test
    public void givenUsingTimer_whenStoppingThread_thenTimerTaskIsCancelled() throws InterruptedException {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        final Timer timer = new Timer("Timer");

        timer.scheduleAtFixedRate(task, 1000L, 1000L);

        Thread.sleep(1000L * 10);
    }

    @Test
    public void givenUsingTimer_whenCancelingTimer_thenCorrect() throws InterruptedException {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        final Timer timer = new Timer("Timer");

        timer.scheduleAtFixedRate(task, 1000L, 1000L);

        Thread.sleep(1000L * 2);
        timer.cancel();
    }

    @Test
    public void givenUsingExecutorService_whenSchedulingRepeatedTask_thenCorrect() throws InterruptedException {
        final TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        final long delay = 1000L;
        final long period = 1000L;
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 3);
        executor.shutdown();
    }

}
