package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch2dMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length==0){
            return false;
        }
        List<Integer> firstPass = new ArrayList<>();
        for (int[] ints : matrix) {
            firstPass.add(ints[0]);
        }

        int index = Math.abs(Collections.binarySearch(firstPass, target) + 1) - 1;
        if(index<0 || index>=matrix.length){
            return false;
        }
        int binarySearch = Arrays.binarySearch(matrix[index], target);
        return binarySearch >= 0;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 3, 5, 7},
                                    { 10,11,16,20},
                                    { 23,30,34,50}};

        boolean b = searchMatrix(matrix, 23);
        System.out.println(b);
    }
}
