package com.ajbose.learning.linkedlist;

/**
 * This program finds the nth from last element in a singly linked list.
 */
public class FindNthFromLast {

    public static void main(String[] args) {
        LinkedListNode<Integer> list = new LinkedListNode<>(10);
        for(int i=0;i<10;i++){
            list.appendToTail(i);
        }
        System.out.println(list.toString());
        System.out.println(findNthFromLast(list,4));

        list.appendToTail(10);
        list.appendToTail(11);
        list.appendToTail(11);
        System.out.println(list.toString());
        System.out.println(findNthFromLast(list,4));
    }

    /**
     * Returns the kth element from last from a linked list.
     *
     * @param firstNode
     * @param k
     * @return
     */
    public static <T> T findNthFromLast(LinkedListNode<T> firstNode, int k) {

        LinkedListNode<T> tempNode1 = firstNode;
        LinkedListNode<T> tempNode2 = null;
        int i = 1;
        while (tempNode1.next != null) {
            tempNode1 = tempNode1.next;
            i++;
            if (null == tempNode2) {
                if (i == k) {
                    tempNode2 = firstNode;
                }
            } else {
                tempNode2 = tempNode2.next;
            }

        }
        return tempNode2.data;
    }
}
