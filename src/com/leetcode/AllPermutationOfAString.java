package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class AllPermutationOfAString {

    public static void main(String[] args) {
        Set<String> permutations = generateAllPermutation("abc");
        permutations.forEach(System.out::println);
    }

    private static Set<String> generateAllPermutation(String str) {
        Set<String> permutations = new HashSet<>();
        return generateAllPermutation(str.toCharArray(), permutations, 0);
    }

    private static Set<String> generateAllPermutation(char[] str, Set<String> permutations, int l) {
        if(l==str.length-1) return permutations;
        permutations.add(String.valueOf(str));
        for (int i = l; i < str.length; i++) {

                swap(str, i, l);
                generateAllPermutation(str, permutations, l+1);
                swap(str, i, l);

        }
        return permutations;
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
