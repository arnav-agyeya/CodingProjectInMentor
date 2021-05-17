package com.leetcode;

import java.util.*;

public class DeepCopyGraph {


    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public void addNeighbour(Node node){
            neighbors.add(node);
        }


    }

    private class NewNode extends Node{

        public NewNode() {
        }

        public NewNode(int _val) {
            super(_val);
        }

        public NewNode(int _val, ArrayList<Node> _neighbors) {
            super(_val, _neighbors);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }


    private Node nc;
    private Set<Node> set = new HashSet<>();

    public void dfs(Node node, Node copy) {
        if (node == null || set.contains(node)) return;

        System.out.println(node.val);

        set.add(node);

        if (node.neighbors == null) return;

        for (Node n : node.neighbors) {
            Node node1 = copy.neighbors.stream()
                                       .filter(nig -> nig.val == n.val)
                                       .findAny()
                                       .orElse(new NewNode(n.val));
            copy.neighbors.add(node1);
            dfs(n, node1);
        }
    }

    public Node cloneGraph(Node node) {
        set.clear();
        if (node == null) return null;

        nc = new NewNode(node.val);
        dfs(node, nc);

        return nc;

    }

    private static class Solution{
        public static void main(String[] args) {
            DeepCopyGraph copyGraph = new DeepCopyGraph();
            Node n1 = new Node(1);
            Node n2 = new Node(2);
            Node n3 = new Node(3);
            Node n4 = new Node(4);

            n1.addNeighbour(n2);
            n1.addNeighbour(n3);

            n2.addNeighbour(n1);
            n2.addNeighbour(n4);

            n3.addNeighbour(n1);

            n4.addNeighbour(n2);

            Node node = copyGraph.cloneGraph(n1);
            copyGraph.cloneGraph(node);

        }
    }
}
