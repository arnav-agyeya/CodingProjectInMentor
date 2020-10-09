package com.leetcode;

public class RotateLinkedListKTimes {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public static void main(String[] args) {
            RotateLinkedListKTimes rotateLinkedListKTimes = new RotateLinkedListKTimes();
//            ListNode h4 = new ListNode(5);
//            ListNode h3 = new ListNode(4, h4);
//            ListNode h2 = new ListNode(3, h3);
            ListNode h1 = new ListNode(2);
            ListNode head = new ListNode(1, h1);

            rotateLinkedListKTimes.rotateRight(head, 0);
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){ return null; }
        ListNode headCopy = head;
        ListNode tailCopy;
        int size = 1;
        while(head.next != null) {head = head.next; size++;}

        int r = size - k%size;
        tailCopy = head;
        head = headCopy;
        if(r==0 || size == 1 || r==size){
            return head;
        }
        r = r-1;
        ListNode tail = head;
        ListNode tailF = head.next;
        while(r!=0) {tail = tail.next; tailF = tailF.next; r--;}

        //tailF is head now, tail is current tail, tailCopy is previous tail
        tail.next = null;
        tailCopy.next = head;

        return tailF;

    }
}
