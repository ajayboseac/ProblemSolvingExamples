package com.ajbose.learning.trees;

import java.util.Arrays;
import java.util.List;

/**
 * Check Balanced: Implement a function to check if a binary tree is balanced.
 * A binary tree is balanced if the height of any two subtress should not
 * differ by more than one.
 */
public class CheckIfTreeIsBalanced {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5,10,11,12,34,23,45,22);
        Node rootNode = BinarySearchTreeFromSortedArray.makeTree(array, 0, array.size()-1,null);
        System.out.println(rootNode.toString());
        System.out.println(new CheckIfTreeIsBalanced().checkIfBalanced(rootNode));
        System.out.println(new CheckIfTreeIsBalanced().checkHeight(rootNode));
    }

    public boolean checkIfBalanced(Node rootNode){
        if (rootNode==null){
            return true;
        }
        if(Math.abs(heightOfSubTree(rootNode.left)- heightOfSubTree(rootNode.right))>1){
            return false;
        }
        return checkIfBalanced(rootNode.left)&&checkIfBalanced(rootNode.right);
    }

    public int heightOfSubTree(Node node){
        if (node==null){
            return 1;
        }
        return Math.max(1+heightOfSubTree(node.left),1+heightOfSubTree(node.right));
    }


    public int checkHeight(Node rootNode){
        if (rootNode==null){
            return -1;
        }
        int leftHeight = checkHeight(rootNode.left);
        int rightHeight = checkHeight(rootNode.right);
        if(leftHeight==Integer.MIN_VALUE||rightHeight==Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        if(leftHeight-rightHeight>1){
            return Integer.MIN_VALUE;
        }
        return Math.max(leftHeight,rightHeight)+1;
    }
}
