package org.milan.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link SynchronizedHashMapWithReadWriteLock}
 *
 * @author Milan Rathod
 */
class SynchronizedHashMapWithReadWriteLockTest {

    @Test
    void whenReading_ThenMultipleReadingAllowed() throws InterruptedException {
        SynchronizedHashMapWithReadWriteLock object = new SynchronizedHashMapWithReadWriteLock();
        final int threadCount = 5;
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        executeReaderThreads(object, threadCount, service);

        Thread.sleep(1000);

        assertTrue(object.isReadLockAvailable());

        service.shutdown();
    }

    private void executeReaderThreads(SynchronizedHashMapWithReadWriteLock object, int threadCount, ExecutorService service) {
        for (int i = 0; i < threadCount; i++)
            service.execute(() -> object.get("key" + threadCount));
    }

}