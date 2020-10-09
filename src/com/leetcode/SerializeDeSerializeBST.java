package com.leetcode;

import java.util.Stack;

public class SerializeDeSerializeBST {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }

        public static void main(String[] args)
        {
            TreeNode tn1 = new TreeNode(7);
            TreeNode tn2 = new TreeNode(3);
            TreeNode tn3 = new TreeNode(9);
            TreeNode tn4 = new TreeNode(2);
            TreeNode tn5 = new TreeNode(5);
            TreeNode tn6 = new TreeNode(8);
            TreeNode tn7 = new TreeNode(10);

            tn1.left = tn2;
            tn1.right = tn3;
            tn2.left = tn4;
            tn2.right = tn5;
            tn3.left = tn6;
            tn3.right = tn7;
            SerializeDeSerializeBST bst = new SerializeDeSerializeBST();
            String serializeBst = bst.serialize(tn1);
            System.out.println(serializeBst);
            TreeNode deserialize = bst.deserialize(serializeBst);
            System.out.println(bst.serialize(deserialize));
        }
    }

    public String serialize(TreeNode root) {
        String serializeTree = serializeTree(root);

        if(!serializeTree.isEmpty()) {
            serializeTree = serializeTree.substring(0, serializeTree.length()-1);
        }

        serializeTree="["+ serializeTree +"]";

        return serializeTree;
    }

    private String serializeTree(TreeNode root) {
        if(root == null){
            return "";
        }
        String str = root.val + ",";
        str+= serializeTree(root.left);
        str+=serializeTree(root.right);

        return str;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data.equals("[]")) return null;

        String substring = data.substring(1, data.length() - 1);
        String[] numbers = substring.split(",");

        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null;

        return constructTree(numbers, stack, root);
    }

    private TreeNode constructTree(String[] numbers, Stack<TreeNode> stack, TreeNode root) {
        for(String num : numbers){

            int parseInt = Integer.parseInt(num);
            TreeNode treeNode = new TreeNode(parseInt);

            if(stack.empty()){
                root = treeNode;
            }
            else if(parseInt > stack.peek().val){

                TreeNode lastPopped = null;

                while (!stack.empty() && stack.peek().val < parseInt){
                    lastPopped = stack.pop();
                }

                assert lastPopped != null;
                lastPopped.right = treeNode;

            }
            else {

                stack.peek().left = treeNode;
            }
            stack.push(treeNode);
        }

        return root;
    }
}
