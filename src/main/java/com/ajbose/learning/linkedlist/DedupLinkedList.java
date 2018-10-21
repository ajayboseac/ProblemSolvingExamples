package com.ajbose.learning.linkedlist;

import java.util.HashSet;

/**
 * This program removes Duplicates from a linked list.
 */
public class DedupLinkedList {

    public static void main(String[] args) {
        LinkedListNode<Integer> list = new LinkedListNode<>(10);
        for(int i=0;i<10;i++){
            list.appendToTail(i);
        }
        System.out.println("Before Deduping: "+list.toString());
        dedupLinkedList(list);
        System.out.println("After Deduping: "+list.toString());

        list.appendToTail(10);
        list.appendToTail(11);
        list.appendToTail(11);
        System.out.println("Before Deduping: "+list.toString());
        dedupLinkedList(list);
        System.out.println("After Deduping: "+list.toString());
    }

    public static <T> void dedupLinkedList(LinkedListNode<T> startNode){
        HashSet<T> set = new HashSet<T>(10);
        LinkedListNode<T> temp = startNode;
        LinkedListNode<T> previous = null;
        while(temp != null){
            if(set.contains(temp.data)){
                previous.next = temp.next;
            }else {
                set.add(temp.data);
                previous = temp;
            }
            temp = temp.next;
        }
    }

}
