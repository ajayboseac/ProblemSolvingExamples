package com.ajbose.learning.graph;

import java.util.List;

public class Node {
    public int nodeId;
    List<Node> children;
    Status status = Status.UNVISITED;
    public Node(int nodeId) {
        this.nodeId = nodeId;
    }
}

enum Status{
    VISITED,VISITING,UNVISITED;
}
