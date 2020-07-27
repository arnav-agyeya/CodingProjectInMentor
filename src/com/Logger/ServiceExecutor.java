package com.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceExecutor {
    private static ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    private static ExecutorService executorService2 = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        ILogClient client = new LogClientImpl(10);
        client.start("PID_3", System.currentTimeMillis());
        callSleep();
        client.start("PID_1", System.currentTimeMillis());
        callSleep();
        client.start("PID_2", System.currentTimeMillis());
        callSleep();
        runEndInThread1(client, "PID_1");
        runPollInThread2(client);
        runEndInThread1(client, "PID_3");
        runPollInThread2(client);
        runEndInThread1(client, "PID_2");
        runPollInThread2(client);
        runPollInThread2(client);
        runPollInThread2(client);
        System.out.println("All Complete");

        return;
    }

    private static void callSleep() {
        //If sleep not introduced two PID are getting started at the same time
        //Hence causing some data to miss
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runEndInThread1(ILogClient client, String pid) {
        executorService1.execute(() -> endProcess(client, pid));
    }

    private static void runPollInThread2(ILogClient client) {
        executorService2.execute(() -> pollLogger(client));
    }

    private static void pollLogger(ILogClient client) {

        String pollResult = client.poll();
        System.out.println(pollResult == null ? "No Entry" : pollResult);
    }

    private static void endProcess(ILogClient client, String pID) {
        client.end(pID);
    }
}
