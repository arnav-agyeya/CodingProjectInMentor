package com.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/explore/challenge/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3483/
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        int res = 0;
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int last = -1;
        for (int[] interval : intervals) {
            if (interval[1]<=last) {
                res++;
            } else {
                last = interval[1];
            }
        }
        return intervals.length-res;
    }

    private static class Solution {
        public static void main(String[] args) {
            RemoveCoveredIntervals removeCoveredIntervals = new RemoveCoveredIntervals();
            int[][] intervals = new int[][]{{1, 2}, {1, 4}, {3, 4}};
            int coveredIntervals = removeCoveredIntervals.removeCoveredIntervals(intervals);
            System.out.println(coveredIntervals);
        }
    }
}
