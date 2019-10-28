package com.ajbose.learning.trees.random;

import java.util.Random;

public class Node {

    int data;
    Node rightChild;
    Node leftChild;
    int size = 0;

    Node(int data) {
        this.data = data;
    }

    public void insert(int data) {
        if (data < this.data) {
            if (this.leftChild != null) {
                this.leftChild.insert(data);
            } else {
                this.leftChild = new Node(data);
            }
        } else {
            if (this.rightChild != null) {
                this.rightChild.insert(data);
            } else {
                this.rightChild = new Node(data);
            }
        }
        size++;
    }

    public Node findRandomNode(){
        int random = new Random().nextInt(size);
        int leftSize = (leftChild==null)?0:leftChild.size;
        if(random== leftSize){
            return this;
        }
        else if(random<leftSize){
            return leftChild.findRandomNode();
        }else {
            return rightChild.findRandomNode();
        }
    }
}
