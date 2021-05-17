package com.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3356/
 */
public class SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {

        int l=0;
        int r=nums.length-1;

        if(target>nums[r]){
            return r+1;
        }
        if(target<nums[0]){
            return 0;
        }
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid]>target){
                r=mid;
            }
            else{
                l=mid+1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        List<Integer> integerList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int search = Collections.binarySearch(integerList, 7);
        System.out.println(Math.abs(search)-1);

        System.out.println(searchInsert(nums, 7));
    }
}
