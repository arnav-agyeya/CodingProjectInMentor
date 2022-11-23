package com.hackerrank;

import java.util.PriorityQueue;
import java.util.Scanner;

public class LegoBlocks {

    public static int legoBlocks(int n, int m) {
        // Write your code here
        return recur(n,m,0,0);

    }

    public static int recur(int n, int m, int h, int cw){
        if(h == n) return 1;
        if(cw == m) return recur(n,m,h+1, 0);
        if(cw>m) return 0;

        // with block of w = 1,2,3,4
        int w1 = recur(n, m, h, cw+1);
        int w2 = recur(n,m,h,cw+2);
        int w3 = recur(n,m,h,cw+3);
        int w4 = recur(n,m,h,cw+4);

        return w1+w2+w3+w4;
    }


    public static void main(String[] args) {
        int q;
        Scanner sc = new Scanner(System.in);
        q = Integer.parseInt(sc.next());

        for (int i = 0; i < q; i++) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());

            System.out.println(legoBlocks(n,m));
        }
    }
}
