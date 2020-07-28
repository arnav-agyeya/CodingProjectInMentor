package com.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceExecutor {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        ILogClient client = new LogClientImpl(10);
        client.start("PID_3", System.currentTimeMillis());
        client.start("PID_1", System.currentTimeMillis());
        client.start("PID_2", System.currentTimeMillis());
        runEndInThread1(client, "PID_1");
        runPollInThread2(client);
        runEndInThread1(client, "PID_3");
        runPollInThread2(client);
        runEndInThread1(client, "PID_2");
        runPollInThread2(client);
        runPollInThread2(client);
        runPollInThread2(client);
        return;
    }

    private static void runEndInThread1(ILogClient client, String pid) {
        executorService.execute(() -> endProcess(client, pid));
    }

    private static void runPollInThread2(ILogClient client) {
        executorService.execute(() -> pollLogger(client));
    }

    private static void pollLogger(ILogClient client) {

        String pollResult = client.poll();
        System.out.println(pollResult == null ? "No Entry" : pollResult);
    }

    private static void endProcess(ILogClient client, String pID) {
        client.end(pID);
    }
}
