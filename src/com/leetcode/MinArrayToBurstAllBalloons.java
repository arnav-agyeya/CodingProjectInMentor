package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MinArrayToBurstAllBalloons {

    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) {return 0;}
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int currEnd = points[0][1];
        int cnt = 1;
        for(int[] pos : points){
            if(pos[0]>currEnd){
                cnt++;
                currEnd = pos[1];
            }
        }
        return cnt;
    }



}

