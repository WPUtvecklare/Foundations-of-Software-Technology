/*
 * Date: 2020-10-02
 * File Name: MyGraph.java
 * Author: Adam Bergman
 */
package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.DirectedGraph;
import ab224qr_assign3.graphs.Node;

import java.util.*;

/**
 * Class Description: Represents a Directed Graph.
 * A graph contains several nodes and edges that connects the nodes to each other.
 * @version 1.0
 * @author Adam Bergman
 */
public class MyGraph<E> implements DirectedGraph<E> {
    private final Map<E, MyNode<E>> graph;

    public MyGraph() {
        graph = new HashMap<>();
    }

    /**
     * Adds a node by the given "item" if it doesn't already exist.
     * Throws exception if the item is null
     * @param item to add
     * @return The newly constructed or previous Node
     */
    @Override
    public Node<E> addNodeFor(E item) {
        checkIfNull(item);

         if (!containsNodeFor(item)) {
            MyNode<E> nodeToAdd = new MyNode<E>(item);
            graph.put(item, nodeToAdd);
         }

        return graph.get(item);
    }

    /**
     * Returns the node of the given item.
     * Throws exception if the item is null
     * @param item to be returned as a node
     * @return Node representing the item
     */
    @Override
    public Node<E> getNodeFor(E item) {
        checkIfNull(item);

        if (!containsNodeFor(item)) {
            throw new IllegalArgumentException();
        }

        return graph.get(item);
    }

    /**
     * Adds an edge between the nodes represented by "from" and "to"
     * if it doesn't already exist
     * Throws exception if any of the given items are null
     * @param from, source node
     * @param to, target node
     * @return true if the edge was added, else false
     */
    @Override
    public boolean addEdgeFor(E from, E to) {
        checkIfNull(from, to);

        MyNode<E> sourceNode = (MyNode<E>) addNodeFor(from);
        MyNode<E> targetNode = (MyNode<E>) addNodeFor(to);

        // Add the target as successor to the source and add the source as predecessor to the target
        if (!sourceNode.hasSucc(targetNode) && !targetNode.hasPred(sourceNode)) {
            sourceNode.addSucc(targetNode);
            targetNode.addPred(sourceNode);

            return true;
        }

        return false;
    }

    /**
     * Checks if the given node is in the graph
     * Throws exception if the item is null
     * @param item, node to be checked.
     * @return true if it is, else false
     */
    @Override
    public boolean containsNodeFor(E item) {
        checkIfNull(item);
        return graph.containsKey(item);
    }

    /**
     * Returns the number of nodes in the graph
     * @return number of nodes
     */
    @Override
    public int nodeCount() {
        return graph.size();
    }

    /**
     * Returns an iterator over all nodes in the graph
     * @return graph nodes iterator
     */
    @Override
    public Iterator<Node<E>> iterator() {
        HashMap<E, Node<E>> map = new HashMap<>();

        for (MyNode<E> node : graph.values()) {
            map.put(node.item(), node);
        }

        return map.values().iterator();
    }

    /**
     * Returns an iterator over all nodes without any predecessors (in-edges)
     * @return heads iterator
     */
    @Override
    public Iterator<Node<E>> heads() {
        HashMap<E, Node<E>> map = new HashMap<>();

        for (MyNode<E> node : graph.values()) {
            if (node.isHead()) {
                map.put(node.item(), node);
            }
        }

        return map.values().iterator();
    }

    /**
     * Summarizes the number of nodes without any predecessors (in-edges)
     * @return number of head nodes
     */
    @Override
    public int headCount() {
        int count = 0;
        Iterator<Node<E>> itr = heads();

        while (itr.hasNext()) {
            count++;
            itr.next();
        }

        return count;
    }

    /**
     * Returns an iterator over all nodes without any successors (out-edges)
     * @return tails iterator
     */
    @Override
    public Iterator<Node<E>> tails() {
        HashMap<E, Node<E>> map = new HashMap<>();

        for (MyNode<E> node : graph.values()) {
            if (node.isTail()) {
                map.put(node.item(), node);
            }
        }

        return map.values().iterator();
    }

    /**
     * Summarizes the number of nodes without any successors (out-edges)
     * @return number of tail nodes
     */
    @Override
    public int tailCount() {
        int count = 0;
        Iterator<Node<E>> itr = tails();

        while (itr.hasNext()) {
            count++;
            itr.next();
        }

        return count;
    }

    /**
     * Returns a list over all nodes in the graph
     * @return list of nodes
     */
    @Override
    public List<E> allItems() {
        return new ArrayList<E>(graph.keySet());
    }

    /**
     * Summarizes the number of edges in the graph
     * @return edge count
     */
    @Override
    public int edgeCount() {
        int count = 0;

        for (Node<E> node : graph.values()) {
            count += node.outDegree();
        }

        return count;
    }

    /**
     * Removes a node and all its connecting edges.
     * Throws exception if the item is null
     * @param item, node to be removed.
     */
    @Override
    public void removeNodeFor(E item) {
        checkIfNull(item);

        if (!containsNodeFor(item)) {
            throw new NoSuchElementException();
        }

        MyNode<E> toBeRemoved = (MyNode<E>) getNodeFor(item);

        // Remove the predecessor and successor of the item to be removed if any node has it
        for (MyNode<E> node : graph.values()) {
            if (node.hasPred(toBeRemoved)) {
                node.removePred(toBeRemoved);
            }

            if (node.hasSucc(toBeRemoved)) {
                node.removeSucc(toBeRemoved);
            }
        }

        toBeRemoved.disconnect();
        graph.remove(item);
    }

    /**
     * Checks if an edge between the nodes represented by "from" and "to" exists in the graph.
     * Throws exception if any of the given items are null
     * @param from, source node item
     * @param to, target node item
     * @return true if the edge is in the graph, else false
     */
    @Override
    public boolean containsEdgeFor(E from, E to) {
        checkIfNull(from, to);

        if (containsNodeFor(from) && containsNodeFor(to)) {
            MyNode<E> sourceNode = (MyNode<E>) getNodeFor(from);
            MyNode<E> targetNode = (MyNode<E>) getNodeFor(to);

            return sourceNode.hasSucc(targetNode) && targetNode.hasPred(sourceNode);
        }

        return false;
    }

    /**
     * Removes the edge between the nodes represented by "from" and "to" if it exist
     * Throws exception if any of the given items are null
     * @param from, source node item
     * @param to, target node item
     * @return true if the edge was in graph and successfully removed, else false
     */
    @Override
    public boolean removeEdgeFor(E from, E to) {
        checkIfNull(from, to);

        if (containsEdgeFor(from, to)) {
            MyNode<E> sourceNode = (MyNode<E>) getNodeFor(from);
            MyNode<E> targetNode = (MyNode<E>) getNodeFor(to);

            // Remove the target as successor to the source and remove the source as predecessor to the target
            sourceNode.removeSucc(targetNode);
            targetNode.removePred(sourceNode);

            return true;
        }

        return false;
    }

    /**
     * Returns the graph with all it's nodes in a user-friendly format
     * @return the graph items
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (MyNode<E> node : graph.values()) {
            str.append(node.item() + " ==> [ ");

            Iterator<Node<E>> itr = node.succsOf();

            if (!itr.hasNext()) {
                str.append(node);
            }

            while (itr.hasNext()) {
                str.append(itr.next());

                if (itr.hasNext()) {
                    str.append(", ");
                }
            }

            str.append(" ] \n");
        }

        return str.toString();
    }

    /**
     * Checks if any of the given arguments are null and in that case throws an exception
     * @param items to be checked (any amount of item(s))
     */
    @SafeVarargs
    private void checkIfNull(E... items) {
        for (E item : items) {
            if (item == null) {
                throw new NullPointerException();
            }
        }
    }
}
