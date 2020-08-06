package com.dependencyinjection;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.concurrent.ExecutionException;

public class LoggingCaller {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PropertiesModule(), new LoggingModule());

        TaskManager taskManager = injector.getInstance(TaskManager.class);

        try {
            taskManager.execute("This is waited logger logging something.").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
