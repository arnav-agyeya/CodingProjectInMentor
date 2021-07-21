package com.leetcode;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramHash {
    // Complete the sherlockAndAnagrams function below.
    public static int sherlockAndAnagrams(String s) {
        Map<AnagramKey, Integer> anagramKeySet = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                AnagramKey anagramKey = new AnagramKey(substring);
               anagramKeySet.put(anagramKey, anagramKeySet.getOrDefault(anagramKey, 0)+1);
            }
        }

        AtomicInteger res = new AtomicInteger();
        anagramKeySet.forEach((k,v)->{
            System.out.println(k.key+"->"+v);
            res.addAndGet((v * (v - 1)) / 2);
        });
        return res.get();
    }

    private static class AnagramKey{
        public AnagramKey(String key) {
            this.key = key;
        }

        private String key;

        private String getHash(String str){
            return Stream.of(str.split(""))
                          .sorted()
                          .collect(Collectors.joining());
        }

        @Override
        public boolean equals(Object o) {
            if(! (o instanceof AnagramKey)){
                return false;
            }
            String hash = getHash(this.key);
            String hashO = getHash(((AnagramKey) o).key);

            return hash.equals(hashO);
        }

        @Override
        public int hashCode() {
            return Objects.hash(getHash(key));
        }
    }

    public static void main(String[] args) throws IOException {
//        int cdcd = sherlockAndAnagrams("cdcd");
//        System.out.println(cdcd);
//       Boolean b1 = new Boolean("false");
//       Boolean b2 = new Boolean("tRue");
//       Boolean b3 = new Boolean("true");
//       String str = null;
//       if(str != "null"){
//           System.out.println("KK");
//       }
//       Long[] dpa = new Long[]{1L, 2L, 3L, 4L, 5L};
//       Arrays.sort(dpa,Comparator.comparingLong(o -> o));
    }
    private static class Test2{}

    private static void throwIt() {
        throw new RuntimeException();
    }
}
