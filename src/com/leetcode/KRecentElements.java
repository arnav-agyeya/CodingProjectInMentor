package com.leetcode;

import java.util.*;

public class KRecentElements {


    private Map<Integer, Integer> recentElMap = null;

    public int[] topKFrequent(int[] nums, int k) {

        recentElMap = new HashMap<>(k);
        for(int el : nums){
            Integer prevEntry = recentElMap.putIfAbsent(el, 1);
            if(prevEntry!=null){
                recentElMap.put(el, prevEntry +1);
            }
        }
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> recentElMap.get(o)).reversed());

        recentElMap.forEach((j,v)-> {
            System.out.println(j+" "+v);
            queue.add(j);
        });

        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i]= queue.poll();
        }
        return res;
    }


}



