package com.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ExecuteService {

    public static void main(String[] args) {
        LogClientImpl client = new LogClientImpl();
        client.start("PID_1", System.currentTimeMillis());
        client.start("PID_4", System.currentTimeMillis());
        runEndInThread1(client, "PID_1");
        runPollInThread2(client);
        client.start("PID_3", System.currentTimeMillis());
        runEndInThread1(client, "PID_3");
        runPollInThread2(client);
        runEndInThread1(client, "PID_4");
        runPollInThread2(client);
        runPollInThread2(client);
        runPollInThread2(client);
    }

    private static void runEndInThread1(ILogClient client, String pid) {
        new Thread(() -> endProcess(client, pid)).start();
    }

    private static void runPollInThread2(ILogClient client) {
        new Thread(() -> pollLogger(client)).start();
    }

    private static void pollLogger(ILogClient client) {
        try {
            System.out.println(client.poll());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(" Application Terminated");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Request timed out");
        }
    }

    private static void endProcess(ILogClient client, String pid_1) {
        try {
            client.end(pid_1);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(" Application Terminated");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Request timed out: " + pid_1);
        }
    }
}
