package com.ajbose.learning.trees.random;

import java.util.Arrays;
import java.util.List;

/**
 * If you are implementing a binary search tree from scratch which in addition
 * to insert find and delete has a method getRandomNode , which returns a random
 * node from the tree. All nodes should be equally likely to be chosen.
 * Design and implement an algorithm for getRandomNode , and explain how you
 * would implement the rest of the methods.
 */
public class RandomNodeBinaryTree {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 6,7,8,9,10);
        Node rootNode = new Node(5);
        array.stream().forEach(it->rootNode.insert(it));
        Node randomNode = rootNode.findRandomNode();
        System.out.println(randomNode.data);
    }

}
