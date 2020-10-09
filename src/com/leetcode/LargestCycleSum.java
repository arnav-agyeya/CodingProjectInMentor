package com.leetcode;

public class LargestCycleSum {

    private int[] parents;
    private int[] array;
    private int[] size;

    public LargestCycleSum(int[] array){
        int length = array.length;
        this.array = array;
        parents = array;
        size = new int[length];
    }

    public void disjointSetFormation(){

    }
}
