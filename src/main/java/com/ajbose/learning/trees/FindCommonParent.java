package com.ajbose.learning.trees;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FindCommonParent {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        Node rootNode = BinarySearchTreeFromSortedArray.
                makeTree(array, 0, array.size() - 1, null);
        Node commonParent = new FindCommonParent().findCommonParent(rootNode.left, rootNode.right);

        System.out.println(commonParent.data);

        commonParent = new FindCommonParent().findCommonParentWithoutParentLink(rootNode.left, rootNode.right, rootNode);

        System.out.println(commonParent.data);

        commonParent = new FindCommonParent().findCommonParentWithoutParentLinkOptimized(rootNode.left, rootNode.right, rootNode);
        System.out.println(commonParent.data);
    }

    private Node findCommonParentWithoutParentLinkOptimized(Node node1, Node node2, Node rootNode) {
        if (rootNode == null || node1 == null || node2 == null) {
            return null;
        }

        if (rootNode == node1 && rootNode == node2) {
            return rootNode;
        }

        Node x = findCommonParentWithoutParentLinkOptimized(node1, node2, rootNode.left);
        if (x != null && x != node1 && x != node2) {
            return x;
        }

        Node y = findCommonParentWithoutParentLinkOptimized(node1, node2, rootNode.right);
        if (y != null && y != node1 && y != node2) {
            return y;
        }

        if (x != null && y != null && x != y) {
            return rootNode;
        }
        if (node1 == rootNode || node2 == rootNode) {
            return rootNode;
        }

        return x == null ? y : x;
    }

    private Node findCommonParentWithoutParentLink(Node node1, Node node2, Node rootNode) {
        boolean node1IsOnLeft = covers(rootNode.left, node1);
        boolean node2IsOnLeft = covers(rootNode.left, node2);
        if (node1IsOnLeft != node2IsOnLeft) {
            return rootNode;
        }
        return node1IsOnLeft ? findCommonParentWithoutParentLink(node1, node2, rootNode.left) : findCommonParentWithoutParentLink(rootNode.right, node1, node2);
    }


    public Node findCommonParent(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        if (node1 == node2) {
            return node1.parent;
        }
        if (covers(node1, node2)) {
            return node1.parent;
        }
        if (covers(node2, node1)) {
            return node2.parent;
        }

        Node sibling = getSibling(node1);
        while (sibling != null) {
            if (covers(sibling, node2)) {
                return sibling.parent;
            }
            sibling = getSibling(sibling.parent);
        }

        return null;
    }

    private Node getSibling(Node node) {
        if (null == node || null == node.parent) {
            return null;
        }
        Node parent = node.parent;

        if (parent.left == node) {
            return parent.right;
        }
        return parent.left;
    }

    public boolean covers(Node rootNode, Node node) {
        if (rootNode == null) {
            return false;
        }
        if (node == rootNode) {
            return true;
        }
        return covers(rootNode.left, node) || covers(rootNode.right, node);
    }

}
