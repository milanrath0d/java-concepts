package org.milan;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Milan Rathod
 */
public class AsyncFileExample {

    public static void main(String[] args) {
        AsyncFileExample asyncFileExample = new AsyncFileExample();

        asyncFileExample.write("dummy.txt");
    }

    private void write(String filename) {
        final String property = System.getProperty("user.home");

        System.out.println(property);

        Path path = Paths.get(property + "/test/data/" + filename );

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello world".getBytes());
        byteBuffer.flip();

        final File file = path.toFile();


        if (!file.exists()) {
            try {
                Files.createDirectories(path.getParent());
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
                asynchronousFileChannel.write(byteBuffer, 0, byteBuffer, new CompletionHandler<>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println("Write operation completed");
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.err.println("Write operation failed" + exc);
                    }
                });

                TimeUnit.SECONDS.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
