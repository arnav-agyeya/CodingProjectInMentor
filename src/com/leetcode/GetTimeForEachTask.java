package com.leetcode;

import java.util.*;

public class GetTimeForEachTask {

    public static void main(String[] args) {
        Map<String, Node> valueToNodeMap = populateAndGetTaskTree();
        Node headOfTree = findHeadOfTree(valueToNodeMap);
        assert headOfTree!=null;
        dfsAndPopulateCorrectTime(headOfTree);
        System.out.println(headOfTree.timeTaken);

    }

    private static void dfsAndPopulateCorrectTime(Node node) {
        if(node == null){
            return;
        }
        int res = 0;
        for (Node n : node.children) {
            dfsAndPopulateCorrectTime(n);
            res = Math.max(res, n.timeTaken);
        }
        node.timeTaken+=res;
    }

    private static Node findHeadOfTree(Map<String, Node> valueToNodeMap) {
        return valueToNodeMap.values().stream().filter(value -> value.parent == null).findFirst().orElse(null);
    }

    private static Map<String, Node> populateAndGetTaskTree() {
        Scanner scanner = new Scanner(System.in);
        int tasks = Integer.parseInt(scanner.next());
        Map<String, Node> valueToNodeMap = new HashMap<>();
        for (int i = 0; i < tasks; i++) {
            String input = scanner.next();
            String[] split = input.split("->");
            if(valueToNodeMap.containsKey(split[0])){
                Node node = valueToNodeMap.get(split[0]);
                addChildToParent(valueToNodeMap, split, node);

            }
            else{
                Node newNodeParent = new Node(split[0], null);
                valueToNodeMap.put(split[0], newNodeParent);
                addChildToParent(valueToNodeMap, split, newNodeParent);
            }
            if("NULL".equals(split[1])){
                valueToNodeMap.get(split[0]).setTimeTaken(Integer.parseInt(split[2]));
            }
            else{
                valueToNodeMap.get(split[1]).setTimeTaken(Integer.parseInt(split[2]));
            }

        }
        return valueToNodeMap;
    }

    private static void addChildToParent(Map<String, Node> valueToNodeMap, String[] split, Node newNodeParent) {
        if (!"NULL".equals(split[1])) {
            Node newNodeChild;
            if(valueToNodeMap.containsKey(split[1])){
                newNodeChild = valueToNodeMap.get(split[1]);
                newNodeChild.parent = newNodeParent;
            }
            else{
                newNodeChild= new Node(split[1], newNodeParent);
                valueToNodeMap.put(split[1], newNodeChild);
            }
            newNodeParent.children.add(newNodeChild);
        }
    }


    private static class Node {
        String value;
        Node parent;

        public void setTimeTaken(int timeTaken) {
            this.timeTaken = timeTaken;
        }

        int timeTaken = 0;
        Set<Node> children;

        public Node(String value, Node parent) {
            this.value = value;
            this.parent = parent;
            children = new HashSet<>();
        }
    }
}

/*
5
A->B->40
A->C->50
B->D->20
C->E->50
A->NULL->20

 */
