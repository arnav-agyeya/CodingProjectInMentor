package com.leetcode;

public class MergeSortLinkedList {

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
            MergeSortLinkedList mergeSortLinkedList = new MergeSortLinkedList();
            ListNode h4 = new ListNode(4);
            ListNode h3 = new ListNode(5, h4);
            ListNode h2 = new ListNode(2, h3);
            ListNode h1 = new ListNode(3, h2);
            ListNode head = new ListNode(1, h1);

            ListNode listNode = mergeSortLinkedList.sortList(head);
            while (listNode!=null){
                System.out.println(listNode.val);
                listNode = listNode.next;
            }
        }
    }

    public ListNode findMidOfList(ListNode head) {
        if (head == null) return null;

        ListNode next = head;
        ListNode fastNext = head;

        while (fastNext.next != null && fastNext.next.next != null) {
            next = next.next;
            fastNext = fastNext.next.next;
        }
        return next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode res = null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        if (head1.val <= head2.val) {
            res = head1;
            res.next = merge(head1.next, head2);
        }
        else {
            res = head2;
            res.next = merge(head2.next, head1);
        }
        return res;
    }

    public ListNode sortList(ListNode head) {
        // divide
        if (head == null || head.next == null) return head;

        ListNode midOfList = findMidOfList(head);
        ListNode nextOfMid = midOfList.next;
        midOfList.next = null;

        ListNode first = sortList(head);
        ListNode second = sortList(nextOfMid);

        ListNode merge = merge(first, second);

        return merge;
    }
}
