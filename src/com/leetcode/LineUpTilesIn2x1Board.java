package com.leetcode;

public class LineUpTilesIn2x1Board {

    int[]arrayDP;

    public LineUpTilesIn2x1Board(int n) {
        arrayDP = new int[n];


        for(int i = 0; i<n; i++){
            arrayDP[i] = -1;
        }
    }

    public int countWays(int n){
        if(n==0 || n==1)
            return n;

        return arrayDP[n] = countWays(n-1) + countWays(n-2);
    }

    public static void main(String[] args) {
        LineUpTilesIn2x1Board lineUpTilesIn2x1Board = new LineUpTilesIn2x1Board(20);
        int ways = lineUpTilesIn2x1Board.countWays(5);
        System.out.println(ways);
    }
}
