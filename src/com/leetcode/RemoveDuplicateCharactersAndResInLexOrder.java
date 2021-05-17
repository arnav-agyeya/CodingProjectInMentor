package com.leetcode;

import java.util.Stack;

public class RemoveDuplicateCharactersAndResInLexOrder {

    public static String removeDuplicateLetters(String s) {

        int[] charFrequency = new int[26];
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            charFrequency[s.charAt(i) - 'a'] += 1;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!visited[ch - 'a']) {
                while (!stack.empty() && ch < stack.peek() && charFrequency[stack.peek() - 'a'] > 0) {
                    visited[stack.peek() - 'a'] = false;
                    stack.pop();
                }
                visited[ch - 'a'] = true;
                stack.push(ch);

            }
            charFrequency[ch - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.empty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        String aabbccaagg = removeDuplicateLetters("bbaaaaazzccaaggaa");
        System.out.println(aabbccaagg);
    }
}
