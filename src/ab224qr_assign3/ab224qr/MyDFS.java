/*
 * Date: 2020-10-06
 * File Name: MyDFS.java
 * Author: Adam Bergman
 */
package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.DFS;
import ab224qr_assign3.graphs.DirectedGraph;
import ab224qr_assign3.graphs.Node;

import java.util.*;

/**
 * Class Description: Implementation of the Depth-first search algorithm.
 * @version 1.0
 * @author Adam Bergman
 */
public class MyDFS<E> implements DFS<E> {

    /**
     * Returns the nodes visited by depth-first search starting from
     * the provided root node. Each visited note is attached with a
     * depth-first number.
     * @param graph graph
     * @param root node to start from
     * @return list of nodes visited by DFS
     */
    @Override
    public List<Node<E>> dfs(DirectedGraph<E> graph, Node<E> root) {
        checkGraphIsNull(graph);
        checkIfNodeIsNull(root);

         Set<Node<E>> visited = new LinkedHashSet<>();
         doDFS(root, visited);

        return new ArrayList<>(visited);
    }

    /**
     * Returns the nodes visited by depth-first search where all nodes
     * are visited. Each node is attached with a depth-first number.
     * @param graph graph
     * @return list of nodes visited by DFS
     */
    @Override
    public List<Node<E>> dfs(DirectedGraph<E> graph) {
        checkGraphIsNull(graph);

        Set<Node<E>> visited = new LinkedHashSet<>();

        if (graph.headCount() == 0) {
            Node<E> firstNode = graph.getNodeFor(graph.allItems().get(0));
            doDFS(firstNode, visited);
        } else {
            graph.heads().forEachRemaining(node -> doDFS(node, visited));
        }

        return new ArrayList<>(visited);
    }

    /**
     * Performs recursive Depth-First Search by traversing the successors
     * of the provided node and call itself when an unvisited successor
     * was found.
     * @param node root node to start from
     * @param visited all the nodes that have been visited
     */
    private void doDFS(Node<E> node, Set<Node<E>> visited) {
        if (!visited.contains(node)) {
            node.num = visited.size();
            visited.add(node);

            Iterator<Node<E>> successors = node.succsOf();

            while (successors.hasNext()) {
                Node<E> successor = successors.next();

                if (!visited.contains(successor)) {
                    doDFS(successor, visited);
                }
            }
        }
    }

    /**
     * Performs DFS starting from the provided root node
     * and only visits its reachable nodes
     * @param g graph
     * @param root node to start from
     * @return list of nodes ordered as post-order
     */
    @Override
    public List<Node<E>> postOrder(DirectedGraph<E> g, Node<E> root) {
        checkGraphIsNull(g);
        checkIfNodeIsNull(root);

        Set<Node<E>> visited = new HashSet<>();
        List<Node<E>> postOrdered = new ArrayList<>();

        return doPostOrder(root, visited, postOrdered);
    }

    /**
     * Performs DFS on all nodes in the graph and
     * attaches a post-order number to each node.
     * @param g graph
     * @return list of all nodes in the graph, post-ordered
     */
    @Override
    public List<Node<E>> postOrder(DirectedGraph<E> g) {
        checkGraphIsNull(g);

        Set<Node<E>> visited = new LinkedHashSet<>();
        List<Node<E>> postOrdered = new ArrayList<>();

        if (g.headCount() == 0) {
            Node<E> firstNode = g.getNodeFor(g.allItems().get(0));
            doPostOrder(firstNode, visited, postOrdered);
        } else {
            g.heads().forEachRemaining(node -> doPostOrder(node, visited, postOrdered));
        }

        return postOrdered;
    }

    /**
     * Performs DFS on all nodes in the graph and
     * attaches a depth-first number if attach_dfs_number
     * is true, otherwise it attaches a post-order number.
     * @param g graph
     * @param attach_dfs_number if dfs number should be attached or not
     * @return list of all nodes in the graph, post-ordered
     */
    @Override
    public List<Node<E>> postOrder(DirectedGraph<E> g, boolean attach_dfs_number) {
        checkGraphIsNull(g);

        Set<Node<E>> visited = new LinkedHashSet<>();
        List<Node<E>> postOrdered = new ArrayList<>();

        g.heads().forEachRemaining(node -> {
            if (attach_dfs_number) {
                node.num = visited.size();
            }

            doPostOrder(node, visited, postOrdered);
        });

        return postOrdered;
    }

    /**
     * Performs DFS in post-order
     * @param node to start from
     * @param visited set that stores all the visited nodes
     * @param postOrdered all the visited nodes as an array
     * @return list of nodes, post-ordered
     */
    private List<Node<E>> doPostOrder(Node<E> node, Set<Node<E>> visited, List<Node<E>> postOrdered) {
        visited.add(node);

        node.succsOf().forEachRemaining(successor -> {
            if (!visited.contains(successor)) {
                doPostOrder(successor, visited, postOrdered);
            }
        });

        node.num = postOrdered.size();
        postOrdered.add(node);
        return postOrdered;
    }

    /**
     * Checks if the graph contains one or more cycles
     * @param graph graph
     * @return true if it contains cycle(s), else false
     */
    @Override
    public boolean isCyclic(DirectedGraph<E> graph) {
        checkGraphIsNull(graph);

        List<Node<E>> postOrdered = postOrder(graph);

        for (Node<E> node : postOrdered) {
            Iterator<Node<E>> successors = node.succsOf();
            while (successors.hasNext()) {
                if (successors.next().num >= node.num) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Sorts all nodes in the provided graph topological
     * @param graph graph
     * @return list of all nodes, topological-ordered
     */
    @Override
    public List<Node<E>> topSort(DirectedGraph<E> graph) {
        checkGraphIsNull(graph);

        List<Node<E>> postOrdered = postOrder(graph);
        Collections.reverse(postOrdered);
        return postOrdered;
    }

    /**
     * Throws error if the provided graph is null
     * @param g graph
     */
    private void checkGraphIsNull(DirectedGraph<E> g) {
        if (g == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Throws error if the provided node is null
     * @param n node
     */
    private void checkIfNodeIsNull(Node<E> n) {
        if (n == null) {
            throw new NullPointerException();
        }
    }
}
