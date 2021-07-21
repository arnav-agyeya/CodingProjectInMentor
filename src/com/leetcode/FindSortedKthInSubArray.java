package com.leetcode;

import java.util.Scanner;

public class FindSortedKthInSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArray = new String[]{"aaaaaa","bbbbb","ccccc","eeeee","ddddd"};
        int[][] queries = new int[][]{{3,3,3},
                                      {2,5,14},
                                      {1,5,15}};

        int[][]  cmp = new int[strArray.length][26];

        for (int i = 0; i < strArray.length; i++) {
            for(char ch : strArray[i].toCharArray()){
                cmp[i][ch-'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < strArray.length; j++) {
                cmp[j][i] += cmp[j-1][i];
            }
        }

        for (int[] q : queries) {
            int sum = 0;
            for (int i = 0; i < 26; i++) {
                int toSub = q[0]==1? 0: cmp[q[0]-2][i];
                sum += cmp[q[1]-1][i] - toSub;

                if(sum >= q[2]){
                    System.out.println((char) ('a'+i));
                    break;
                }
            }
        }
    }


}
