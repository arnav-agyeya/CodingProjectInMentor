package com.leetcode;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class CountOnes {

    public static void main(String args[] ) throws Exception {

        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.next());

        int[] arr = new int[len];

        for(int i = 0; i< len;i++){
            arr[i] = Integer.parseInt(sc.next());
        }
        int x = Integer.parseInt(sc.next());
        if(len <=2){
            System.out.println(0);
            return;
        }
        Set<String> stringSet = new LinkedHashSet<>();

        for(int i = 0;i<x;i++){
            int[] crr = new int[len];
            for(int j = 1; j<len-1; j++){
                if(arr[j-1] == arr[j+1]){
                    crr[j] = 1;
                }
                else{
                    crr[j] = 0;
                }
            }
            if(crr[0] != 0){
                crr[0] = 0;
            }
            if(crr[len-1] != 0){
                crr[len-1] = 0;
            }
            arr = crr;
            boolean flag = true;
            String str = "";
            for(int j = 0; j< len; j++){
                str += Integer.toString(arr[j]);
            }
            if(stringSet.contains(str)){
//                break;
            }
            else{
                stringSet.add(str);
            }
            System.out.println(str);
        }
        int oneCnt = 0;
        int cc = (x-1)%stringSet.size();
        Iterator<String> iterator = stringSet.iterator();
        String str = "";
        for(int i= 0; i<= cc && iterator.hasNext(); i++){
            str = iterator.next();
        }
        System.out.println("+++ "+ str);
        for(int i = 0; i< len;i++){
            char ch = str.charAt(i);
            oneCnt += ch=='1'?1:0;
        }

        System.out.println(oneCnt);


    }
}
/*
8
1 0 1 0 1 0 1 0
10
*/
/*
6
1 0 1 0 1 0
10
*/
