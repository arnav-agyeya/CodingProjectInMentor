package com.leetcode;

import java.util.*;

/**
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3358/
 */
public class InsertDeleteGetRandomConstantSolution {

    private static class RandomizedSet {

        private Map<Integer, Integer> map = null;
        private List<Integer> list = null;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            int sizeList = list.size();
            list.add(val);
            map.put(val, sizeList);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {

            if (!map.containsKey(val)) {
                return false;
            }
            Integer index = map.get(val);
            if(index>=list.size()){return true;}
            map.remove(val);
            Integer lastEl = list.get(list.size() - 1);
            list.remove(list.size()-1);
            list.set(index, lastEl);
            map.put(lastEl,index);
//            System.out.println("List "+ list);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            Random rnd = new Random();
            int randomInt = rnd.nextInt(list.size());
            return list.get(randomInt);
        }
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
//        System.out.println(set.insert(1));
        System.out.println(set.remove(2));
        System.out.println(set.remove(2));
        System.out.println(set.remove(2));
        System.out.println(set.insert(2));
        System.out.println(set.getRandom());
        System.out.println(set.remove(1));
        System.out.println(set.insert(2));
        System.out.println(set.getRandom());
    }
}
