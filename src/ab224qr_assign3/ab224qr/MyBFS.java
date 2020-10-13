/*
 * Date: 2020-10-06
 * File Name: MyBFS.java
 * Author: Adam Bergman
 */
package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.BFS;
import ab224qr_assign3.graphs.DirectedGraph;
import ab224qr_assign3.graphs.Node;

import java.util.*;

/**
 * Class Description: Implementation of the Breadth-first search algorithm.
 * @version 1.0
 * @author Adam Bergman
 */
public class MyBFS<E> implements BFS<E> {

    /**
     * Returns the nodes visited by Breadth-first search
     * starting from the provided node. Each node is
     * attached with a breadth-first number.
     * @param graph graph
     * @param root node to start from
     * @return list of nodes visited by BFS
     */
    @Override
    public List<Node<E>> bfs(DirectedGraph<E> graph, Node<E> root) {
        if (graph == null || root == null) {
            throw new NullPointerException();
        }

        Queue<Node<E>> toVisit = new LinkedList<>();
        toVisit.add(root);

        return doBFS(toVisit);
    }

    /**
     * Returns the nodes visited by breadth-first search where all nodes
     * are visited. Each node is attached with a breadth-first number.
     * @param graph graph
     * @return list of nodes visited by DFS
     */
    @Override
    public List<Node<E>> bfs(DirectedGraph<E> graph) {
        if (graph == null) {
            throw new NullPointerException();
        }

        Queue<Node<E>> toVisit = new LinkedList<>();

        if (graph.headCount() == 0) {
            Node<E> firstNode = graph.getNodeFor(graph.allItems().get(0));
            toVisit.add(firstNode);
        } else {
            graph.heads().forEachRemaining(toVisit::add);
        }

        return doBFS(toVisit);
    }

    /**
     * Performs BFS by adding the current node to the visited list
     * and browsing through all it's successors and adding them to
     * the "to visit" stack
     * @param toVisit nodes to visit
     * @return list of the nodes visited
     */
    private List<Node<E>> doBFS(Queue<Node<E>> toVisit) {
        Set<Node<E>> visited = new LinkedHashSet<>();

        do {
            Node<E> node = toVisit.poll();

            if (!visited.contains(node)) {
                visited.add(node);
                node.num = visited.size();

                node.succsOf().forEachRemaining(successor -> {
                    if (!visited.contains(successor)) {
                        toVisit.add(successor);
                    }
                });
            }

        } while (!toVisit.isEmpty());

        return new ArrayList<>(visited);
    }
}
