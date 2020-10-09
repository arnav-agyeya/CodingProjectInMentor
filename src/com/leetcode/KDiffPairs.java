package com.leetcode;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KDiffPairs {

    Set<AbstractMap.SimpleEntry<? extends Integer, ? extends Integer>> pairs = new HashSet<>();
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int i=0,j=1;
        while(j < nums.length){
            while(nums[j]-nums[i]>k && i<j){
                i++;
            }
            if(nums[j]-nums[i]==k && i!=j){
                pairs.add(new AbstractMap.SimpleEntry<>(nums[i],nums[j]));
            }
            j++;
        }
        return pairs.size();
    }

    private static class Solution{
        public static void main(String[] args) {
            KDiffPairs pairs = new KDiffPairs();
            int[] nums = new int[]{1,2,3,4,5};
            int pairs1 = pairs.findPairs(nums, 1);
            System.out.println(pairs1);
        }
    }
}

