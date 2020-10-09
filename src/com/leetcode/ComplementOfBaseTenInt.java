package com.leetcode;

public class ComplementOfBaseTenInt {
    public int bitwiseComplement(int N) {
        if(N==0)
            return 1;
        int cnt =0;
        int M = N;
        while (N!=0){
            N = N>>1;
            cnt++;
        }
        System.out.println("bits: "+ cnt);
        return (int) (Math.pow(2,cnt) - 1 - M);
    }

    private static class Solution{
        public static void main(String[] args) {
            ComplementOfBaseTenInt anInt = new ComplementOfBaseTenInt();
            System.out.println(anInt.bitwiseComplement(10));
        }
    }
}
