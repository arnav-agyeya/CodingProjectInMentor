package com.dependencyinjection;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.concurrent.CompletableFuture;

@Singleton
public class TaskManager {

    private ILogger logger;

    @Inject
    public TaskManager(ILogger logger) {
        this.logger = logger;
    }

    public CompletableFuture<Void> execute(String data) {
        for (var word : data.split(" ")) {
            logger.write(word+"\n");
        }

        return logger.flushAsync().whenComplete((o, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                logger.close();
            }
        });
    }


}
