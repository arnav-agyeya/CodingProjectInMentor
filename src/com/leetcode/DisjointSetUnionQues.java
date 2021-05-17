package com.leetcode;

import java.util.*;

public class DisjointSetUnionQues {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int cases = Integer.parseInt(sc.next());
        int cases = 1;
        for (int i = 0; i < cases; i++) {
            int res = findMaximumArmyCount(sc);
            System.out.println(res);
        }
    }

    private static int findMaximumArmyCount(Scanner sc) {
        int friends = Integer.parseInt(sc.next());
        int relations = Integer.parseInt(sc.next());

        Map<Integer, Integer> friendList = new HashMap<>();
        Map<Integer, Integer> sizeOfGroup = new HashMap<>();

        for (int i = 0; i < friends; i++) {
            friendList.put(i + 1, i + 1);
            sizeOfGroup.put(i + 1, 1);
        }
        for (int i = 0; i < relations; i++) {
            int p1 = Integer.parseInt(sc.next());
            int p2 = Integer.parseInt(sc.next());

            unionFriends(friendList, sizeOfGroup, p1, p2);
        }

        int res = sizeOfGroup.entrySet()
                             .stream()
                             .filter(entry-> friendList.get(entry.getKey())==entry.getKey())
                             .min(Comparator.comparingInt(Map.Entry::getValue))
                             .get().getValue();

        friendList.entrySet().forEach(en-> System.out.println(en));

        return res;
    }

    private static void unionFriends(Map<Integer, Integer> friendList, Map<Integer, Integer> sizeOfGroup, int pop1, int pop2) {
        int p1 = findParent(friendList, pop1);
        int p2 = findParent(friendList, pop2);
        if(p1==p2) return;

        if (sizeOfGroup.get(p1) > sizeOfGroup.get(p2)) {
            friendList.put(p2, p1);
            sizeOfGroup.put(p1, sizeOfGroup.get(p2) + 1);
        } else if (sizeOfGroup.get(p2) >= sizeOfGroup.get(p1)) {
            friendList.put(p1, p2);
            sizeOfGroup.put(p2, sizeOfGroup.get(p2) + 1);
        }
//        System.out.println(findParent(friendList, p1) + " : "+ pop1+" , " +pop2);
    }

    private static int findParent(Map<Integer, Integer> friendList, int p1) {
        if (friendList.get(p1) != p1) {
            int parent = findParent(friendList, friendList.get(p1));
            friendList.put(p1, parent);
        }

        return friendList.get(p1);
    }
}
