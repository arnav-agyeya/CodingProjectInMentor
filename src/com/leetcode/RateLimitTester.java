package com.leetcode;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RateLimitTester {
    private static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    private boolean runTest(String resourceId, String customerId, int qps, int qpsToSend, int numSeconds) throws InterruptedException {
        ServiceClient client = new ServiceClient();
        ArrayList<String> config = new ArrayList<String>(List.of(resourceId + " " + customerId + " " + qps));
        RateLimiter rateLimiter = new RateLimiter(config, client);
        int numRequestsSent = 0;
        for (int i = 0 ; i < numSeconds; i++) {
            long thisSecondStart = System.currentTimeMillis();
            long thisSecondEnd = thisSecondStart + 1000;
            int numSentThisSecond = 0;
            int triedToSend = 0;
            int numSlices = 40;
            final int[] sliceNumRequests = new int[numSlices];
            float meanPerSlice =  (float)qpsToSend / numSlices;
            for (int k = 0; k < numSlices; k++) {
                sliceNumRequests[k] = getPoissonRandom(meanPerSlice);
                triedToSend += sliceNumRequests[k];
            }

            float sliceWidth = 1000.0f/numSlices;
            for (int k = 0; k < numSlices; k++) {
                int numThisSlice = sliceNumRequests[k];
                if (numThisSlice > 0) {
                    int sliceWait = (int)(sliceWidth / numThisSlice);
                    for (int j = 0 ; j < numThisSlice; j ++) {
                        long a = System.currentTimeMillis();
                        rateLimiter.callAPI(resourceId, customerId);
                        long b = System.currentTimeMillis();
                        numSentThisSecond++;
                        numRequestsSent++;
                        if (numSentThisSecond >= qpsToSend) {
                            break;
                        }
                        if (System.currentTimeMillis() >= thisSecondEnd) {
                            break;
                        }
                        long c = b - a;
                        if (c < sliceWait) {
                            Thread.sleep(sliceWait - c);
                        }

                    }
                }
                if (numSentThisSecond >= qpsToSend) {
                    break;
                }
                if (System.currentTimeMillis() >= thisSecondEnd) {
                    break;
                }
            }
            System.out.println("Sent " + numSentThisSecond + " requests in second: " + i + ". Tried to send: " + Math.min(triedToSend, qpsToSend));
            int expectedQps = Math.min(qps, Math.min(qpsToSend, triedToSend));
            if (numSentThisSecond > expectedQps + 1) {
                System.out.println("Qps was expected to be " + expectedQps +  " but we were able to send " + numSentThisSecond + " requests in this second");
                return false;
            }
            if (numSentThisSecond < 0.8 * expectedQps) {
                System.out.println("Qps was expected to be " + expectedQps +  " but we were ONLY able to send " + numSentThisSecond + " requests in this second. Not the optimal qps.");
                return false;
            }
        }
        System.out.println("Sent " + numRequestsSent + " requests in Total");
        List<String> logs = client.getRequestLogs();

        if (logs.size() != numRequestsSent) {
            System.out.println("Error, logs do not report the same number of requests sent logs: " + logs.size() + " vs expected: " + numRequestsSent);
            return false;
        } else {
            System.out.println("Number of entries in logs match requests sent");
        }
        List<Long> timeStamps = new ArrayList<>(logs.size());
        for (String s : logs) {
            String [] parts = s.split(" ");
            long t = Long.parseLong(parts[1]);
            timeStamps.add(t);
        }
        return doit(timeStamps, qps);
    }

    private boolean doit(List<Long> log, int qps) {
        long currentWindowStart = -1;
        int currentWindowStartIdx = 0;
        int currentWindowSize = 0;
        int currIdx = 0;
        for (long t : log) {
            if (currentWindowStart < 0) {
                currentWindowStart = t;
                currentWindowSize = 1;
                currentWindowStartIdx = 0;
            } else{
                currentWindowSize ++;
                if (t - currentWindowStart < 1000) {
                    if (currentWindowSize > qps + 1) {
                        System.out.println("Request " + currIdx + " violates " + currentWindowStartIdx + ":" + currentWindowStart + " has size:" + currentWindowSize);
                        return false;
                    }
                } else {
                    long limit = t - 1000;
                    while (currentWindowStartIdx <= currIdx && currentWindowStart < limit) {
                        currentWindowStartIdx ++;
                        currentWindowSize --;
                        currentWindowStart = log.get(currentWindowStartIdx);
                    }
                }
            }
            currIdx ++;
        }
        return true;
    }

    public static void main(String... args) throws InterruptedException {
        RateLimitTester tester = new RateLimitTester();
        int[] qpsValues = new int[]{30, 40, 100};
        int[] qpsToSendValues = new int[]{25, 30, 120};
        int[] durationValues = new int[]{2, 10, 5};
        for (int i = 0 ; i < qpsValues.length; i ++) {
            System.out.println("---------------------------------------------------------------------------");
            int qps = qpsValues[i];
            int qpsToSend = qpsToSendValues[i];
            int duration = durationValues[i];
            System.out.println("TEST-" + i + ": Qps Limit: " + qps + " but, sending requests at " + qpsToSend + " requests per second to see if limits are respected for " + duration + " seconds");

            if (!tester.runTest("GOOGLE_CALENDAR", "CustomerA", qps, qpsToSend, duration)) {
                System.out.println("FAILED: Test failed for qps: " + qps + " duration: " + duration);
            } else {
                System.out.println("PASSED: Test passed for qps: " + qps + " duration: " + duration);
            }
        }
    }
}

