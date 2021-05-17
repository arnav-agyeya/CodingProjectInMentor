package com.leetcode;

import java.util.*;

public class MaximiseGenieProfit {

    public static void main(String[] args) {
        List<Long> pickup = new ArrayList<>(Arrays.asList(11L, 30L, 0L, 21L, 41L, 19L));
        List<Long> drop = new ArrayList<>(Arrays.asList(20L, 31L, 17L, 22L, 46L, 21L));
        List<Integer> tip = new ArrayList<>(Arrays.asList(6, 1, 9, 0, 8, 0));

        Long maximumProfit = findMaximumProfit(pickup, drop, tip);
        System.out.println(maximumProfit);
    }


    private static long findMaximumProfit(List<Long> pickup, List<Long> drop, List<Integer> tip) {
        List<Triplet> orders = new ArrayList<>();
        for (int i = 0; i < pickup.size(); i++) {
            orders.add(new Triplet(pickup.get(i), drop.get(i), tip.get(i)));
        }
        orders.sort(Comparator.comparing(Triplet::getPickUp));
        Map<Long, Long> dpMap = new HashMap<>();
        return calculateWithDP(orders, 0, 0,dpMap);
    }

    private static long calculateWithDP(List<Triplet> orders, int index, long spos, Map<Long, Long> dpMap) {
        if (index >= orders.size()) {
            return 0L;
        }
        if(dpMap.containsKey(spos)){
            return dpMap.get(spos);
        }
        if (orders.get(index).pickUp < spos) {
            long put = calculateWithDP(orders, index + 1, spos, dpMap);
            dpMap.put(spos, put);
            return put;
        }
        long profit = orders.get(index).calculateProfit();
        long aLong = Math.max(profit + calculateWithDP(orders, index + 1, orders.get(index).dropOff,
                dpMap),
                calculateWithDP(orders, index + 1, spos, dpMap));
        dpMap.put(spos, aLong);
        return aLong;
    }

    private static class Triplet {
        long pickUp;
        long dropOff;
        int tip;

        public Triplet(long pickUp, long dropOff, int tip) {
            this.pickUp = pickUp;
            this.dropOff = dropOff;
            this.tip = tip;
        }

        public long getPickUp() {
            return pickUp;
        }

        public long getDropOff() {
            return dropOff;
        }

        public int getTip() {
            return tip;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pickUp, dropOff, tip);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Triplet)) return false;
            Triplet triplet = (Triplet) o;
            return pickUp == triplet.pickUp && dropOff == triplet.dropOff && tip == triplet.tip;
        }

        public long calculateProfit() {
            return tip + dropOff - pickUp;
        }
    }
}
