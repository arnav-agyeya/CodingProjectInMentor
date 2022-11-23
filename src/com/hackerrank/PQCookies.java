package com.hackerrank;

import javax.sound.midi.Soundbank;
import java.util.*;

public class PQCookies {

    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        if (A.size() <=1) return -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compare);

        pq.addAll(A);
        int cnt = 0;

        Queue<Integer> q = new LinkedList<>();

        while(pq.size()>1 && pq.peek()<k){
            int i1 = pq.poll();
            int i2 = pq.poll();

            pq.add(i1+(2*i2));
            cnt++;
        }

        return cnt;

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(sc.next()));
        }

        System.out.println(cookies(k, arr));
    }
}
