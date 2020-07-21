package com.Logger;

import java.util.*;

public class LogClientImpl implements ILogClient {

    private Map<String, IProcess> processMap = null;
    private volatile TreeMap<Long, String> activeProcessQueue = null;
    private final String NO_ACTIVE_PROCESS = "No active process in the processing queue.";
    private final String ACTIVE_PROCESS_EXECUTION_COMPLETE =
            "Process in the processing queue with process id= %s is complete. Start time: %d , End time: %d";
    private final String ACTIVE_PROCESS_EXECUTION_RUNNING =
            "Process in the processing queue is running. Queue size: %d";

    public LogClientImpl() {
        processMap = new HashMap<>();
        activeProcessQueue = new TreeMap<>((o1, o2) -> (int) (o1-o2)){
//            @Override
//            public String put(long key, String value) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//
//
//                }
//                return super.put(key, value);
//            }
        };
    }

    /**
     * When a process starts, it calls 'start' with processId.
     *
     * @param processId
     * @param timestamp
     */
    @Override
    public void start(String processId, long timestamp) {
        processMap.put(processId, new Process(processId, timestamp));
        activeProcessQueue.put(timestamp, processId);
        poll();
    }

    /**
     * When the same process ends, it calls 'end' with processId.
     *
     * @param processId
     */
    @Override
    public void end(String processId) {
        processMap.get(processId).setEndTime(System.currentTimeMillis());
        poll();
    }

    /**
     * Polls the first log entry of a completed process sorted by the start time of processes in the below format
     * {processId} started at {startTime} and ended at {endTime}
     * <p>
     * process id = 1 --> 12, 15
     * process id = 2 --> 8, 12
     * process id = 3 --> 7, 19
     * <p>
     * {3} started at {7} and ended at {19}
     * {2} started at {8} and ended at {12}
     * {1} started at {12} and ended at {15}
     */
    @Override
    public String poll() {
        String res = "";

        if (activeProcessQueue.isEmpty()) {
            System.out.println(NO_ACTIVE_PROCESS);
            return NO_ACTIVE_PROCESS;
        }

        long firstProcessStartTime = activeProcessQueue.keySet().iterator().next();
        String earliestProcessId = activeProcessQueue.get(firstProcessStartTime);
        System.out.println("\nEarliest process: "+res + " : "+earliestProcessId);
        IProcess earliestProcess = processMap.get(earliestProcessId);
        if(earliestProcess.getEndTime()!=-1){
            activeProcessQueue.remove(firstProcessStartTime);
            System.out.println("removed: "+ " : "+earliestProcessId);
            res = String.format(ACTIVE_PROCESS_EXECUTION_COMPLETE, earliestProcessId, earliestProcess.getStartTime(), earliestProcess.getEndTime());
            System.out.println(res);
            return res;
        }

        res = String.format(ACTIVE_PROCESS_EXECUTION_RUNNING, activeProcessQueue.size());
        System.out.println(res + " : "+earliestProcessId);
        return res;
    }

}
