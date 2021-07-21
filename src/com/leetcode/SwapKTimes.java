package com.leetcode;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SwapKTimes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.next();
        int swapsAllowed = Integer.parseInt(sc.next());
        char[] charArray = string.toCharArray();
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            char minChar = 'z';
            int index = -1;
            for (int j = i+1; j < string.length(); j++) {
                if(minChar>=charArray[j]){
                    minChar = charArray[j];
                    index = j;
                }
            }
            if(minChar>=charArray[i])
                continue;
            charArray[index] = charArray[i];
            charArray[i] = minChar;
            count++;
            if(count == swapsAllowed)
                break;
        }

        System.out.println(String.valueOf(charArray));
    }


}
