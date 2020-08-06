package com.dependencyinjection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Singleton
public class WaitedLogger implements ILogger {

    private OutputStream stream;
    private String identifier;
    private List<String> buffer;

    @Inject
    public WaitedLogger(@Named("file-output-stream") OutputStream stream,
                        @Named("logger.waited.identifier") String identifier) {
        this.stream = stream;
        this.identifier = identifier;
        this.buffer = new ArrayList<>();
    }

    @Override
    public boolean write(String data) {
        buffer.add(identifier + " " + data+ "\n");
        return true;
    }

    @Override
    public CompletableFuture<Void> flushAsync() {
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (String str : buffer) {
            future = future.thenAcceptAsync(o -> {
                try {
                    Thread.sleep(1000);
                    stream.write(str.getBytes());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }, executorService);
        }
        return future;
    }

    @Override
    public boolean close() {
        return LoggingHelper.close(stream);
    }
}
