package com.leetcode;

import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        String str = "11[geek]";
        String res = decodeString(str);
        System.out.println(res);
    }

    private static String decodeString(String str) {
        Stack<String> characterStack = new Stack<>();
        StringBuilder res = new StringBuilder();
        StringBuilder accumulated = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch =str.charAt(i);
            if(ch == '[') continue;
            if(ch == ']' && !characterStack.isEmpty()){

                while (!characterStack.isEmpty() && !Character.isDigit(characterStack.peek().charAt(0))){
                    String pop = characterStack.pop();
                    accumulated.insert(0, pop);
                }
                StringBuilder dig = new StringBuilder();
                while (!characterStack.isEmpty() && Character.isDigit(characterStack.peek().charAt(0))){
                    dig.insert(0, characterStack.pop());
                }
                int num = Integer.parseInt(dig.toString());
                String now = accumulated.toString();
                accumulated.append(now.repeat(Math.max(0,num-1)));
                characterStack.push(accumulated.toString());
                accumulated = new StringBuilder();
            }
            else {
                characterStack.push(Character.toString(ch));
            }

        }

        while (!characterStack.isEmpty()){
            res.insert(0, characterStack.pop());
        }
        return res.toString();
    }
}
