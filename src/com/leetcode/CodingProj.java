package com.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @param {string} s
 * @return {number}
 */
class CodingProj {
    public int getLengthOfLongestSubstring(String str) {
        // Solution goes here...
        if(str == null || str.isEmpty()){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 1;
        int sIn = 0;
        int lIn = 0;

        while(sIn<str.length() && lIn<str.length()){
            char ch = str.charAt(lIn);
            if(map.containsKey(ch) && map.get(ch)>=sIn){
                sIn = map.get(ch) +1;
            }
            map.put(ch, lIn);
            maxLength = Math.max(maxLength, lIn - sIn + 1);
            lIn++;
        }

        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {

            int ret = new CodingProj().getLengthOfLongestSubstring("");

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}


