package com.ajbose.learning.graph;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Given a directed acyclic graph w.a.p to find if there is a route between two nodes.
 */
public class RouteBetweenNodes {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        graph.buildGraph(new File("src/main/resources/graph.txt"));
        int point1=11;
        int point2=15;
        System.out.println(graph.checkPathExists(point1, point2));
        // Check if there is route between nodes.
    }


}
