package com.ajbose.learning.linkedlist;

/**
 * This linkedlist implementation will be used for all our programs involving linkedList.
 * @param <T>
 */
public class LinkedListNode<T> {

    T data;
    LinkedListNode<T> next;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public void appendToTail(T data) {
        LinkedListNode<T> newLinkedListNode = new LinkedListNode<>(data);
        LinkedListNode<T> temp = this;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newLinkedListNode;
    }

    @Override
    public String toString() {
        LinkedListNode<T> temp = this;
        StringBuilder stringBuilder = new StringBuilder();
        while(true){
            stringBuilder.append(temp.data);
            if(temp.next ==null){
                break;
            }
            else {
                stringBuilder.append("->");
            }
            temp=temp.next;
        }
        return stringBuilder.toString();
    }
}
