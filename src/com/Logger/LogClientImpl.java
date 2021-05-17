package com.Logger;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of ILogClient
 */
public class LogClientImpl implements ILogClient {

    private ConcurrentHashMap<String, IProcess> processMap;
    private ConcurrentSkipListMap<Long, LinkedHashSet<String>> activeProcessQueue;
    private BlockingQueue<CompletableFuture<String>> pendingCallsForPoll;
    private List<ExecutorService> threadPool;
    private Lock lock = new ReentrantLock();

    public LogClientImpl(int threadSize) {
        processMap = new ConcurrentHashMap<>();
        activeProcessQueue = new ConcurrentSkipListMap<>(Comparator.comparingInt(Long::intValue));
        pendingCallsForPoll = new LinkedBlockingQueue<>();
        threadPool = new ArrayList<>() {{
            for (int i = 0; i < threadSize; i++) {
                add(Executors.newSingleThreadExecutor());
            }
        }};
    }

    /**
     * When a process starts, it calls 'start' with processId.
     *
     * @param processId
     * @param timestamp
     */
    @Override
    public void start(String processId, long timestamp) {
        threadPool.get(processId.hashCode() % threadPool.size())
                  .execute(() -> startProcess(processId, timestamp));
    }

    /**
     * When the same process ends, it calls 'end' with processId.
     *
     * @param processId
     */
    @Override
    public void end(String processId) {
        threadPool.get(processId.hashCode() % threadPool.size()).execute(() -> {
            endProcess(processId);
        });
    }

    private void endProcess(String processId) {
        processMap.get(processId).setEndTime(System.currentTimeMillis());
        String result;
        lock.lock();
        try {
            if (!pendingCallsForPoll.isEmpty() && (result = pollNow()) != null) {
                try {
                    pendingCallsForPoll.take().complete(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    public String poll() {

        var result = new CompletableFuture<String>();
        lock.lock(); //lock required while working on pendingCallsForPoll as it is used by end() as well.
        try {
            if (pendingCallsForPoll.isEmpty()) {
                return pollNow();
            } else {
                pendingCallsForPoll.offer(result);
            }
        } finally {
            lock.unlock();
        }
        try {
            return result.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Timed Out");
        }
    }

    private void startProcess(String processId, long timestamp) {
        processMap.put(processId, new Process(processId, timestamp));
        activeProcessQueue.putIfAbsent(timestamp, new LinkedHashSet<>());
        activeProcessQueue.get(timestamp).add(processId);
    }

    private String pollNow() {
        if (!activeProcessQueue.isEmpty()) {
            Map.Entry<Long, LinkedHashSet<String>> firstProcessEntry = activeProcessQueue.firstEntry();
            for (String processId : firstProcessEntry.getValue()) {
                if (getProcessFromProcessMap(processId).getEndTime() != -1) {
                    firstProcessEntry.getValue().remove(processId);
                    if (firstProcessEntry.getValue().isEmpty()) {
                        activeProcessQueue.pollFirstEntry();
                    }
                    return getResStringIfProcessComplete(getProcessFromProcessMap(processId).getProcessID(),
                            getProcessFromProcessMap(processId));
                }
            }
        }
        return null;
    }

    private String getResStringIfProcessComplete(String processID, IProcess processFromProcessMap) {
        return processID + " completed start time: "
               + processFromProcessMap.getStartTime()
               + " ,end time: " + processFromProcessMap.getEndTime();
    }

    private IProcess getProcessFromProcessMap(String processID) {
        return processMap.get(processID);
    }

}
