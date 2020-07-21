package com.leetcode;

import com.utils.FastReader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Queue Reconstruction by Height
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3352/
 * Solution
 */
public class QueueReconsByHeight {

    public static int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] -o2[1];
            }
            return o2[0] - o1[0];
        });
        for (int[] person : people) {
            System.out.print(person[0] + "-" + person[1] + " ");
        }
        List<int[]> result = new LinkedList<>();
        for (int[] person : people) {
            if(person[1]<result.size()) {
                result.add(person[1], person);
            }
            else {
                result.add(person);
            }
        }


        return result.toArray(new int[0][]);
    }


    public static void main(String[] args) {

        int n;
        FastReader reader = new FastReader();
//        n = reader.nextInt();
        int[][] queue = new int[][]//{{5,1},{5,2},{5,3},{5,4},{5,0}};
                {{9,0},{7,0},{1,9},{3,0},{2,7},{5,3},{6,0},{3,4},{6,2},{5,2}};
//        for(int i=0;i<n;i++){
//            queue[i][0] = reader.nextInt();
//            queue[i][1] = reader.nextInt();
//        }

        int[][] result = reconstructQueue(queue);
        System.out.println(" ");
        for (int[] person : result) {
            System.out.print(person[0] + "-" + person[1] + " ");
        }

    }
}
