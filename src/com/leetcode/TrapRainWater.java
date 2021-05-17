package com.leetcode;

public class TrapRainWater {

    public int calculateTrappedWater(int[] arr, int n){
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = arr[0];
        rightMax[n-1] = arr[n-1];

        for (int i = 1; i < n; i++) {
            int lastIndex = n-1-i;
            leftMax[i] = Math.max(leftMax[i-1], arr[i]);
            rightMax[lastIndex] = Math.max(rightMax[lastIndex+1], arr[lastIndex]);
        }
        int res = 0;
        for(int i = 1; i < n-1; i++){
            res += Math.max(Math.min(leftMax[i], rightMax[i])-arr[i], 0);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7,4,0,9};
        TrapRainWater trapRainWater = new TrapRainWater();
        int calculateTrappedWater = trapRainWater.calculateTrappedWater(arr, 4);
        System.out.println(calculateTrappedWater);
    }
}
