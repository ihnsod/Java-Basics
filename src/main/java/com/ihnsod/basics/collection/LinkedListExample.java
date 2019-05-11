package com.ihnsod.basics.collection;

/**
 * @author: Ihnsod
 * @create: 2018/09/12 22:33
 **/
public class LinkedListExample {

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        ListNode node1 = node.next = new ListNode(2);
        ListNode node2 = node1.next = new ListNode(3);
        ListNode node3 = node2.next = new ListNode(4);
        ListNode node4 = node3.next = new ListNode(5);
        node4.next = null;

        System.err.println(node);
//        ListNode reverse = reverse(node);
        ListNode reverse = reverse1(node);
        System.err.println(reverse);
    }


    public static ListNode reverse(ListNode head) {
        ListNode newHead = null;
        ListNode now;
        while (head != null) {
            now = head;
            head = head.next;
            now.next = newHead;
            newHead = now;
        }
        return newHead;
    }

    public static ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverse1(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

}

class ListNode {
    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
