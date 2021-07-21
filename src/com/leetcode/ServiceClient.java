package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ServiceClient implements  ServiceInterface {

    private List<String> requestLog = new ArrayList<>();

    public boolean callService(String resourceName, String customerId) {
        String key = resourceName + "_" + customerId;
        long currMsecs = System.currentTimeMillis();
        String log = key + " " + currMsecs;
        requestLog.add(log);
        return true;
    }

    public List<String> getRequestLogs() {
        return requestLog;
    }
}
