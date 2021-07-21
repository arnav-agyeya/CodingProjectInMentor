package com.leetcode;

import java.util.Arrays;
import java.util.OptionalLong;

public class LongestIncreasingSubSeq {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 10, 2, 1, 20};
        int lengthOfLongestIncreasingSubsequence = getLengthOfLongestIncreasingSubSequence(arr);
        System.out.println(lengthOfLongestIncreasingSubsequence);
    }

    private static int getLengthOfLongestIncreasingSubSequence(int[] arr) {
        int[] lis = new int[arr.length];
        Arrays.fill(lis,1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[j]<arr[i] && lis[i] < lis[j]+1){
                    lis[i] = lis[j]+1;
                }
            }
        }

        OptionalLong max = Arrays.stream(lis).mapToLong(n -> (long) n).max();
        return (int)max.getAsLong();
    }
}
