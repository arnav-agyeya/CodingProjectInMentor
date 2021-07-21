package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3}, {2,6}, {8, 10}, {15, 18}};

        int[][] mergeIntervals = mergeIntervals(intervals);

        for (int[] interval : mergeIntervals){
            System.out.println(interval[0]+"->"+interval[1]);
        }
    }

    private static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<PairNum> result = new ArrayList<>();
        PairNum currentInterval = null;
        for(int[] interval : intervals){
            if(currentInterval == null){
                currentInterval = new PairNum(interval[0], interval[1]);
            }
            else{
                if(currentInterval.end>=interval[0]){
                    currentInterval.end = Math.max(currentInterval.end, interval[1]);
                }
                else{
                    result.add(currentInterval);
                    currentInterval = new PairNum(interval[0], interval[1]);
                }
            }
        }
        if(currentInterval!=null){
            result.add(currentInterval);
        }
        List<int[]> collect = result.stream().map(res -> new int[]{res.start, res.end}).collect(Collectors.toList());
        return collect.toArray(int[][]::new);
    }

    private static class PairNum{
        int start;
        int end;

        public PairNum(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
