package com.ajbose.learning.trees;

/**
 * Algorithm to find in order successor of a binary search tree.
 */
public class SuccessorInBinaryTree {

    public Node findSuccesor(Node node){

        if(node.right !=null){
            return findLeftMostNode(node.right);
        }

        Node parent = node.parent;
        while(parent!=null && node != parent.left){
            node =  parent;
            parent = node.parent;
        }

        return parent;
    }

    private Node findLeftMostNode(Node node) {
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
}
