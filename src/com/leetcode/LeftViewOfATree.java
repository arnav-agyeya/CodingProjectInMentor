package com.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeftViewOfATree {

    List<Integer> leftView(Node root) {
        if (root == null) return null;
        List<Integer> view = new ArrayList<>();
        Queue<NodeHeightPair> queue = new ArrayDeque<>();
        queue.add(new NodeHeightPair(root, 0));
        view.add(root.data);
        System.out.println(root.data);
        int heightEncountered = 0;
        while (!queue.isEmpty()) {
            NodeHeightPair polledPair = queue.poll();
            Node nodePolled = polledPair.node;
            if (polledPair.height > heightEncountered) {
                System.out.println(nodePolled.data);
                view.add(nodePolled.data);
                heightEncountered = polledPair.height;
            }
            if(nodePolled.left != null) {
                queue.add(new NodeHeightPair(nodePolled.left, polledPair.height+1));
            }
            if(nodePolled.right != null) {
                queue.add(new NodeHeightPair(nodePolled.right, polledPair.height+1));
            }
        }

        return view;
    }

    private static class NodeHeightPair {
        Node node;
        int height;

        public NodeHeightPair(Node node, int height) {
            this.node = node;
            this.height = height;
        }
    }

    private static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        //7 5 3 6 2 N 1 11 11 6 13 N 6 6 12
        LeftViewOfATree leftViewOfATree = new LeftViewOfATree();
        Node n1 = new Node(7);
        Node n2 = new Node(5);
        Node n3 = new Node(3);
        Node n4 = new Node(6);
        Node n5 = new Node(2);
        Node n6 = null;
        Node n7 = new Node(1);
        Node n8 = new Node(11);
        Node n9 = new Node(11);
        Node n10 = new Node(6);
        Node n11 = new Node(13);
        Node n12 = null;
        Node n13 = new Node(6);
        Node n14 = new Node(6);
        Node n15 = new Node(12);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        n4.right = n9;
        n5.left = n10;
        n5.right = n11;
        n7.left = n12;
        n7.right = n13;
        n8.left = n14;
        n8.right = n15;

        leftViewOfATree.leftView(n1);
    }
}
