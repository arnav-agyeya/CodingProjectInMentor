package com.leetcode;

import java.util.*;

public class FindWinnerOfBerries {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.next());
        for (int i = 0; i < cases; i++) {
            runCases(sc);
            System.out.println();
        }
    }

    private static void runCases(Scanner sc) {
        int treesCount = Integer.parseInt(sc.next());
        List<Integer> berries = new ArrayList<>();

        for (int i = 0; i < treesCount; i++) {
            berries.add(Integer.parseInt(sc.next()));
        }
        long sum = berries.stream().mapToLong(Integer::longValue).sum();
        Map<Integer, Integer> dpmap1 = new HashMap<>();
        Map<Integer, Integer> dpmap2 = new HashMap<>();
        int berriesWithRule1ForRed = getBerriesWithRule1ForRed(berries, dpmap1, 0, berries.size() - 1);
        int berriesWithRule2ForRed = getBerriesWithRule2ForRed(berries, dpmap2, 0, berries.size() - 1);

        printResult(sum, berriesWithRule1ForRed);
        System.out.print(" ");
        printResult(sum, berriesWithRule2ForRed);
    }

    private static void printResult(long sum, int berriesWithRule1ForRed) {
        if (berriesWithRule1ForRed * 2L > sum) {
            System.out.print("Red");
        } else if (berriesWithRule1ForRed * 2L == sum) {
            System.out.print("Tie");
        } else {
            System.out.print("Green");
        }
    }

    private static int getBerriesWithRule1ForRed(List<Integer> berries, Map<Integer, Integer> dpmap, int a, int b) {
        if(a>b || a>=berries.size() || b<=0){
            return 0;
        }

        if(dpmap.containsKey(getKey(a,b))){
            return dpmap.get(getKey(a,b));
        }
        int ans =0;
        ans = Math.max(ans,berries.get(a)+
                           Math.min(getBerriesWithRule1ForRed(berries, dpmap, a+2, b),
                                   getBerriesWithRule1ForRed(berries, dpmap, a+1, b-1)));
        ans = Math.max(ans,berries.get(b)+
                           Math.min(getBerriesWithRule1ForRed(berries, dpmap, a, b-2),
                                   getBerriesWithRule1ForRed(berries, dpmap, a+1, b-1)));

        dpmap.put(getKey(a,b), ans);
        return ans;
    }

    private static int getBerriesWithRule2ForRed(List<Integer> berries, Map<Integer, Integer> dpmap, int a, int b) {
        if(a>b || a>=berries.size() || b<=0){
            return 0;
        }

        if(dpmap.containsKey(getKey(a,b))){
            return dpmap.get(getKey(a,b));
        }
        int ans =0;
        ans = Math.max(ans,berries.get(a)+
                           Math.min(getBerriesWithRule2ForRed(berries, dpmap, a+2, b),
                                   getBerriesWithRule2ForRed(berries, dpmap, a+1, b-1)));
        ans = Math.max(ans,berries.get(b)+
                           Math.min(getBerriesWithRule2ForRed(berries, dpmap, a, b-2),
                                   getBerriesWithRule2ForRed(berries, dpmap, a+1, b-1)));
        if(berries.get(a)==berries.get(b)){
            ans = Math.max(ans, berries.get(a)+berries.get(b)+
                                Math.min(getBerriesWithRule2ForRed(berries, dpmap, a+2, b-1),
                                        getBerriesWithRule2ForRed(berries, dpmap, a+1, b-2)));
        }

        dpmap.put(getKey(a,b), ans);
        return ans;
    }

    private static int getKey(int a, int b) {
        return a*1000+b;
    }


}
