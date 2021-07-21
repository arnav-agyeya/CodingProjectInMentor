package com.leetcode;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaxUniqueSubStr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String maxSubStr = findMaxSubStr(str);
        System.out.println(maxSubStr);
    }


    private static String findMaxSubStr(String str) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int i = 0; int j=1;
        AbstractMap.SimpleEntry<Integer, Integer> beginEndIndexs = new AbstractMap.SimpleEntry<>(i, 0);
        charIndexMap.put(str.charAt(0), 0);
        while (i < str.length() && j < str.length()) {
            char charAt = str.charAt(j);
            if(charIndexMap.containsKey(charAt) && charIndexMap.get(charAt)>=i){
                i = charIndexMap.get(charAt)+1;
            }
            else {
                if((j-i)>(beginEndIndexs.getValue()-beginEndIndexs.getKey())){
                    beginEndIndexs = new AbstractMap.SimpleEntry<>(i,j);
                }
            }
            charIndexMap.put(charAt, j);
            j++;
        }

        return str.substring(beginEndIndexs.getKey(),beginEndIndexs.getValue()+1);
    }
}
