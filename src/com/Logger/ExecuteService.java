package com.Logger;

public class ExecuteService {

    public static void main(String[] args) {
        LogClientImpl client = new LogClientImpl();
        client.start("PID_1",System.currentTimeMillis());
        client.start("PID_4",System.currentTimeMillis());
        client.end("PID_1");
        client.poll();
        client.start("PID_3",System.currentTimeMillis());
        client.end("PID_3");
        client.poll();
        client.end("PID_4");
        client.poll();
        client.poll();
        client.poll();
    }
}
