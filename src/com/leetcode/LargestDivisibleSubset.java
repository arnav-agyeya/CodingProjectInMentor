package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3359/
 */
public class LargestDivisibleSubset {
    public static List<Integer> largestDivisibleSubset(int[] nums) {

        if (nums.length==0){
            return new ArrayList<>();
        }
        int[] cNums = nums;
        Arrays.sort(cNums);

        int length = nums.length;
        int[] prev = new int[length];
        int[] divs = new int[length];
        int maxVal =0;
        divs[0] = 1;
        prev[0] = -1;
        for (int i = 1; i < length; i++) {
            divs[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (cNums[i] % cNums[j] == 0) {
                    if (divs[i] < divs[j] + 1) {
                        divs[i] = divs[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            if(divs[maxVal]<divs[i]){
                maxVal = i;
            }
        }

        List<Integer> res = new ArrayList<>() ;
        while (maxVal>=0){
            res.add(cNums[maxVal]);
            maxVal = prev[maxVal];
        }


        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[0];
        System.out.println(largestDivisibleSubset(nums));
    }
}
