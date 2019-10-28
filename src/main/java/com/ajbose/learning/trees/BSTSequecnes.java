package com.ajbose.learning.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements
 * Print all possible arrays that could have lead to this tree.
 */
public class BSTSequecnes {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5, 10, 11, 12, 34, 23, 45, 22);
        Node rootNode = BinarySearchTreeFromSortedArray.makeTree(array, 0, array.size() - 1, null);
        List<LinkedList<Integer>> linkedLists = findAllSequences(rootNode);
        linkedLists.stream().forEach(it -> {
            it.stream().forEach(it1 -> {
                System.out.print(it1 + ",");
            });
            System.out.println();
        });

    }

    private static List<LinkedList<Integer>> findAllSequences(Node rootNode) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        if (rootNode == null) {
            result.add(new LinkedList<>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(rootNode.data);

        List<LinkedList<Integer>> leftSequences = findAllSequences(rootNode.left);
        List<LinkedList<Integer>> rightSequences = findAllSequences(rootNode.right);

        for (LinkedList<Integer> sequence1 : leftSequences) {
            for (LinkedList<Integer> sequence2 : rightSequences) {
                List<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveSequence(sequence1, sequence2, prefix, weaved);
                result.addAll(weaved);
            }
        }

        return result;
    }

    private static void weaveSequence(LinkedList<Integer> first, LinkedList<Integer> second,
                                      LinkedList<Integer> prefix, List<LinkedList<Integer>> result) {
        if (first.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            list.addAll(prefix);
            list.addAll(second);
            result.add(list);
            return;
        }

        if (second.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            list.addAll(prefix);
            list.addAll(first);
            result.add(list);
            return;
        }

        Integer headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveSequence(first, second, prefix, result);
        first.addFirst(headFirst);
        prefix.removeLast();

        Integer headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveSequence(first,second,prefix,result);
        prefix.removeLast();
        second.addFirst(headSecond);
    }
}
