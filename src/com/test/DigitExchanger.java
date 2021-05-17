package com.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DigitExchanger {

    public static String exchangeDigits(int num){
        if(num > 1000000){
            return "Wrong Output";
        }

        String res = "";
        while(num>0){
            int lastDig = num % 10;
            res += 9 - lastDig;
            num = num / 10;
        }

        return new StringBuilder(res).reverse().toString();

    }

    public static void printInOrder(){

        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> countMap = new LinkedHashMap<>();
        countMap.put(1,0);
        countMap.put(0,0);
        countMap.put(2,0);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int input = sc.nextInt();
            countMap.put(input, countMap.getOrDefault(input, 0)+1);
        }

        countMap.forEach((key, val)->{
            for (int i = 0; i < val; i++) {
                System.out.print(key+" ");
            }
        });
    }

    public static void main(String[] args) {
        printInOrder();
    }
}
