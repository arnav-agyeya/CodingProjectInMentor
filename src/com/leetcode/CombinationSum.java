package com.leetcode;

import java.util.*;

/**
 * https://leetcode.com/explore/challenge/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3481/
 */
public class CombinationSum {

    private void calculateRecursively(int[] candidates, int index, List<Integer> numbers, int target,
                                      Set<List<Integer>> uniqueSet) {
        if (target == 0) {
            Collections.sort(numbers);
            uniqueSet.add(new ArrayList<>(numbers));
        }
        for (int i = index; i < candidates.length; i++) {
            int num = candidates[i];
            if (num <= target) {
                numbers.add(num);
                calculateRecursively(candidates, i, numbers, target - num, uniqueSet);
                numbers.remove(numbers.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> uniqueSet = new HashSet<>();
        calculateRecursively(candidates, 0, new ArrayList<>(), target, uniqueSet);
        return new ArrayList<>(uniqueSet);
    }

    private static class Solution{
        public static void main(String[] args) {
            CombinationSum sum = new CombinationSum();
            int[] candidates = new int[]{2,3,5};
            List<List<Integer>> lists = sum.combinationSum(candidates, 8);
            System.out.println(lists);
        }
    }
}


