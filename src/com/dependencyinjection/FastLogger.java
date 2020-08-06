package com.dependencyinjection;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;


@Singleton
public class FastLogger implements ILogger {

    private OutputStream stream;
    private String identifier;
    private byte[] buffer;
    private int currentSize;

    @Inject
    public FastLogger(@Named("console-output-stream") OutputStream stream,
                      @Named("logger.fast.identifier") String identifier,
                      @Named("logger.fast.buffer.size") int bufferSize) {
        this.stream = stream;
        this.identifier = identifier;
        this.buffer = new byte[bufferSize];
    }

    @Override
    public boolean write(String data) {
        byte[] bytes = (identifier + " " + data).getBytes();
        if ((currentSize + bytes.length) > buffer.length) {

            return false;
        }
        System.arraycopy(bytes, 0, buffer, currentSize, bytes.length);
        currentSize += bytes.length;
        return true;
    }

    @Override
    public CompletableFuture<Void> flushAsync() {
        return CompletableFuture.runAsync(this::flush);
    }

    @Override
    public boolean close() {
        return LoggingHelper.close(stream);
    }

    private void flush() {
        try {
            stream.write(buffer);
            stream.flush();
            currentSize = 0;
        } catch (IOException e) {
            throw new RuntimeException("Logging Error");
        }
    }
}
