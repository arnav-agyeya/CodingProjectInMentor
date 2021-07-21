package com.leetcode;

import java.util.Arrays;

public class CoinChangeDP {
    long[] dpa;
    public long count(int nums[], int m, int n)
    {
        //code here.
        dpa = new long[n+1];

        Arrays.sort(nums);
        for(int i = 0; i<=n; i++){
            dpa[i] = -1L;
        }

        dpCount(nums, dpa, n);
        return dpa[n];
    }

    public long dpCount(int[] nums, long[] dpa, int currAmt){
        if(currAmt<0){
            return 0L;
        }

        if(currAmt == 0){
            return dpa[currAmt] = 1L;
        }
        if(dpa[currAmt]!=-1L){
            return dpa[currAmt];
        }

        long res = Long.MAX_VALUE;
        for(int coin : nums){
            int tt = currAmt - coin;

            if(currAmt>=coin){
                long sa = dpCount(nums, dpa, tt);
                if(sa != Long.MAX_VALUE){
                    res = Math.min(sa+1, res);
                }
            }
        }


        return dpa[currAmt] = res;
    }

    public static void main(String[] args) {
        CoinChangeDP coinChangeDP = new CoinChangeDP();
        int[] coins = {1,2};

        System.out.println(coinChangeDP.count(coins, 2, 4));
        for (long l : coinChangeDP.dpa) {
//            System.out.println(l);
        }
    }

}
