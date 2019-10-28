package com.ajbose.learning.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    Map<Integer, Node> nodes = new HashMap<Integer,Node>();


    Graph(){

    }

    /**
     * Construct the graph from file.
     *
     * @param file
     */
    public void buildGraph(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("\\s+");
            int nodeId = Integer.parseInt(split[0]);
            Node node = getNodeFromId(nodeId);
            if(split.length>1){
                List<Node> children = Arrays.asList(split[1].split(",")).stream().
                        map(string -> getNodeFromId(Integer.parseInt(string)))
                        .collect(Collectors.toList());
                node.children=children;
            }
        }
    }

    private Node getNodeFromId(int nodeId) {
        Node node = nodes.get(nodeId);
        if (null == node) {
            node = new Node(nodeId);
            nodes.put(nodeId, node);
        }
        return node;
    }



    public boolean checkPathExists(int startNodeId, int endNodeId) {
        Node startNode = nodes.get(startNodeId);
        Node endNode = nodes.get(endNodeId);
        LinkedList<Node> queue= new LinkedList<Node>();
        startNode.status=Status.VISITING;
        queue.add(startNode);
        while(!queue.isEmpty()){
            Node currentNode = queue.remove();
            currentNode.status=Status.VISITED;
            if(currentNode==endNode){
                return true;
            }
            if(null==currentNode.children){
                continue;
            }
            currentNode.children.forEach(child-> {
                if(child.status==Status.UNVISITED){
                    child.status=Status.VISITING;
                    queue.add(child);
                }
            });
        }
        return false;
    }
}
