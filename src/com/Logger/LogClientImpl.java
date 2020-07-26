package com.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of ILogClient
 */
public class LogClientImpl implements ILogClient {

    private ConcurrentHashMap<String, IProcess> processMap;
    private ConcurrentSkipListMap<Long, String> activeProcessQueue;
    private CopyOnWriteArrayList<CompletableFuture<String>> completableFutures;
    private Lock lock = new ReentrantLock();

    public LogClientImpl() {
        processMap = new ConcurrentHashMap<>();
        activeProcessQueue = new ConcurrentSkipListMap<>(Comparator.comparingInt(Long::intValue));
        completableFutures = new CopyOnWriteArrayList<>();
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
    }

    /**
     * When the same process ends, it calls 'end' with processId.
     *
     * @param processId
     */
    @Override
    public void end(String processId) throws InterruptedException, ExecutionException, TimeoutException {
        lock.lock();
        try {
            processMap.get(processId).setEndTime(System.currentTimeMillis());
            if (processId.equals(activeProcessQueue.firstEntry().getValue()) && !completableFutures.isEmpty()) {
                System.out.println(poll());
                CompletableFuture<String> completableFuture = completableFutures.remove(0);
                completableFuture.complete(getResStringIfProcessComplete(processId, processMap.get(processId)));
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * Polls the first log entry of a completed process sorted by the start time of processes in the below format
     * {processId} started at {startTime} and ended at {endTime}
     * process id = 3 --> 7, 19
     * <p>
     * {3} started at {7} and ended at {19}
     * {2} started at {8} and ended at {12}
     * {1} started at {12} and ended at {15}
     */
    @Override
    public String poll() throws ExecutionException, InterruptedException, TimeoutException {

        lock.lock();
        try {
            var result = new CompletableFuture<String>();
            if (!activeProcessQueue.isEmpty() && getProcessFromProcessMap(activeProcessQueue.firstEntry()).getEndTime() != -1L) {
                Map.Entry<Long, String> longStringEntry = activeProcessQueue.pollFirstEntry();
                String res = getResStringIfProcessComplete(getProcessFromProcessMap(longStringEntry).getProcessID(),
                        getProcessFromProcessMap(longStringEntry));
                result.complete(res);
            } else {
                completableFutures.add(result);
            }
            return result.get(1, TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }

    }

    private String getResStringIfProcessComplete(String processID, IProcess processFromProcessMap) {
        return processID + " completed start time: "
                + processFromProcessMap.getStartTime()
                + " ,end time: " + processFromProcessMap.getEndTime();
    }

    private IProcess getProcessFromProcessMap(Map.Entry<Long, String> longStringEntry) {
        return processMap.get(longStringEntry.getValue());
    }

}
