package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TreeHeight {
    public static void main(String args[] ) throws Exception {

        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.next());

        for(int i = 0; i<testCases; i++){
            calculateHeight(sc);
        }

    }
    public static void calculateHeight(Scanner sc){
        int n = Integer.parseInt(sc.next());
        Node root = null;
        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = Integer.MIN_VALUE;
        Map<Integer, Node> mp = new HashMap<>();
        for(int i = 0; i< n; i++){
            Node rootC = root;
            int num = Integer.parseInt(sc.next());

            if(root == null){
                root = new Node(num);
                mp.put(num, root);
                minSoFar = num;
                maxSoFar = num;
                continue;
            }
            if(num > maxSoFar){
                Node node = mp.get(maxSoFar);
                node.right = new Node(num);
                mp.put(num, node.right);
            }
            else if(num < minSoFar){
                Node node = mp.get(minSoFar);
                node.left = new Node(num);
                mp.put(num, node.left);
            }
            else{

            }
            while(true){

                if(num > rootC.val){
                    if(rootC.right !=null)
                        rootC = rootC.right;
                    else{
                        rootC.right = new Node(num);
                        break;
                    }
                }
                Boolean vis = Boolean.FALSE;
                vis = Boolean.TRUE;

                if(num < rootC.val ){
                    if(rootC.left !=null)
                        rootC = rootC.left;
                    else{
                        rootC.left = new Node(num);
                        break;
                    }
                }
            }
        }
        int heightOfTree = calculateHeightOfTree(root);
        System.out.println(heightOfTree);
    }

    private static int calculateHeightOfTree(Node node) {

        if (node == null)
            return 0;

        int lDepth = calculateHeightOfTree(node.left);
        int rDepth = calculateHeightOfTree(node.right);

        if (lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }

    private static class Node{
        int val;
        Node left;
        Node right;
        public Node(int val){
            this.val = val;
            left = null;
            right = null;
        }
    }
}
/*
1
5
5 3 4 1 2
*/
