package com.leetcode;

import java.util.*;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = new int[]{22,54,2,4,5,78,66,68};
        int sizeOfLIS = findLISSize(arr);
        System.out.println(sizeOfLIS);
    }

    private static int findLISSize(int[] arr) {
        Integer[] mm = new Integer[arr.length];
        Arrays.fill(mm, 1);
        List<Integer> list = Arrays.asList(mm);
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i]>arr[j] && list.get(i)< list.get(j)+1){
                    list.set(i, list.get(j)+1);
                }
            }
        }

        return list.stream().max(Integer::compare).get();
    }


}
