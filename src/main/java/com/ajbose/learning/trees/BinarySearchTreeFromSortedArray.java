package com.ajbose.learning.trees;

import java.util.Arrays;
import java.util.List;

/**
 * Create a binary search tree of minimal height from a sorted array.
 */
public class BinarySearchTreeFromSortedArray {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        Node rootNode = makeTree(array, 0, array.size()-1,null);
        System.out.println(rootNode.toString());
    }


    public static Node makeTree(List<Integer> array, int first, int last,Node parent) {
        if(last<first){
            return null;
        }
        int mid = (last + first) / 2;
        Node node = new Node(array.get(mid));
        node.parent=parent;
        node.left = makeTree(array, first, mid - 1,node);
        node.right = makeTree(array, mid + 1, last,node);
        return node;
    }
}
