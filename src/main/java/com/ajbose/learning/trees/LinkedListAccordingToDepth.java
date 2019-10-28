package com.ajbose.learning.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a  binary tree , design an algorithm which creates a linked list of all the nodes at each depths.
 * Eg. If you have tree of D depth. You create D linkedLists.
 */
public class LinkedListAccordingToDepth {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5,6,7,8,9,10);
        Node rootNode = BinarySearchTreeFromSortedArray.
                makeTree(array, 0, array.size() - 1, null);
        ArrayList<LinkedList<Node>> linkedLists = new ArrayList<>(10);
        createLinkedListForDepth(rootNode, 0, linkedLists);
        linkedLists.stream().forEach(it -> {
            it.stream().forEach(node->{System.out.print(node.data+",");});
            System.out.println();
        });
    }

    private static void createLinkedListForDepth(Node rootNode, int depth,
                                                 List<LinkedList<Node>> linkedLists) {
        if (rootNode == null) {
            return;
        }

        LinkedList<Node> linkedList = null;
        if (depth < linkedLists.size()) {
            linkedList = linkedLists.get(depth);
        }
        if (null == linkedList) {
            linkedList = new LinkedList<Node>();
            linkedLists.add(linkedList);
        }
        linkedList.add(rootNode);

        createLinkedListForDepth(rootNode.left, depth + 1, linkedLists);
        createLinkedListForDepth(rootNode.right, depth + 1, linkedLists);
    }
}
