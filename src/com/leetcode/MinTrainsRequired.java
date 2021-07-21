package com.leetcode;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MinTrainsRequired {

    public static void main(String[] args) {
        String[] arrival = new String[]{"0900", "0940", "0950", "1100", "1500", "1800"};
        String[] departure = new String[]{"0910", "1200", "1120", "1130", "1900", "2000"};
        Map<String, Integer> map = new TreeMap<>(String::compareTo);
        for (int i = 0; i < arrival.length; i++) {
            map.put(arrival[i], map.getOrDefault(arrival[i], 0)+1);
            map.put(departure[i], map.getOrDefault(departure[i], 0)-1);
        }

        int max = -1;
        int res = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            res += value;
            max = Math.max(res, max);
        }


        System.out.println(max);
    }
}
