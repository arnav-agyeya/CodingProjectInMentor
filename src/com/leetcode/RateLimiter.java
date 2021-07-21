package com.leetcode;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * limits: They define the limit for each resource, customer_id pair. Each line in limits is a
 * 3-tuple in the following format <resouceId customerId maxQps>
 * client: Use the client.callAPI() to invoke the service. This always returns true.
 * <p>
 * The task is to ensure that you never exceed the QPS of each resourceId, customerId specified in limits when calling callAPI.
 */
public class RateLimiter {
    ServiceInterface client;
    Map<CustomerIdResourceIdPair, RateLimiterBucket> customerIdResourceIdPairToQSPMap;

    public RateLimiter(List<String> limits, ServiceInterface client) {
        // Parse the limits variable to find out what the limits for each resourceId customerId pair into whatever datastructure that is convenient to you.
        this.client = client;

        customerIdResourceIdPairToQSPMap =
                limits.stream()
                      .map(limit -> limit.split(" "))
                      .collect(Collectors.toMap(limit -> getCustomerIdResourceIdPair(limit[0], limit[1]),
                                limit -> new RateLimiterBucket(Integer.parseInt(limit[2]))));
    }

    private CustomerIdResourceIdPair getCustomerIdResourceIdPair(String requestId, String customerId) {
        return new CustomerIdResourceIdPair(requestId, customerId);
    }

    /**
     * This is the ONLY method that you need to implement.
     * returns: A long representing the number of milliseconds that callAPI should wait before invoking client.callService in order to respect the limits for this resourceId, customerId pair (as specified in the limits passed to the constructor).
     */
    private long canExecute(String resourceId, String customerId) {
        CustomerIdResourceIdPair customerIdResourceIdPair = getCustomerIdResourceIdPair(resourceId, customerId);
        RateLimiterBucket rateLimiterBucket = customerIdResourceIdPairToQSPMap.get(customerIdResourceIdPair);
        Long timeToWait = rateLimiterBucket.getTimeToWait();
        return timeToWait;
    }

    /**
     * Anyone who wants to invoke ServiceInterface.callService needs to do so through the RateLimiter to do this in a rate-limited manner. The rate limiter will wait to make the call if necessary in order to respect the qps
     */
    public boolean callAPI(String resourceId, String customerId) throws InterruptedException {
        long waitTime = canExecute(resourceId, customerId);
        if (waitTime > 0) {
            Thread.sleep(waitTime);
        }
        addRequestToBucket(resourceId, customerId);
        this.client.callService(resourceId, customerId);
        return true;
    }

    private void addRequestToBucket(String resourceId, String customerId) {
        CustomerIdResourceIdPair customerIdResourceIdPair = getCustomerIdResourceIdPair(resourceId, customerId);
        customerIdResourceIdPairToQSPMap.get(customerIdResourceIdPair).add();
    }

    private static class CustomerIdResourceIdPair {
        String customerId;
        String resourceId;

        public CustomerIdResourceIdPair(String customerId, String resourceId) {
            this.customerId = customerId;
            this.resourceId = resourceId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerId+"#"+resourceId);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CustomerIdResourceIdPair)) return false;
            CustomerIdResourceIdPair that = (CustomerIdResourceIdPair) o;
            return customerId.equals(that.customerId) && resourceId.equals(that.resourceId);
        }
    }

    private static class RateLimiterBucket{
        LinkedList<Long> requests;
        int maxQSP;

        public RateLimiterBucket(int maxQSP) {
            this.requests = new LinkedList<>();
            this.maxQSP = maxQSP;
        }

        public Long getTimeToWait() {
            if(requests.size() < maxQSP){
                return 0L;
            }
            Long earliestCall = requests.get(0);
            long diffOfTime = System.currentTimeMillis() - earliestCall;
            if(diffOfTime < 1000L) {

                return 1000L - diffOfTime;
            }
            requests.poll();
            return 0L;
        }

        public void add() {
            if(requests.size()>maxQSP){
                requests.poll();
            }
            requests.add(System.currentTimeMillis());
        }
    }

}

