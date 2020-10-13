/*
 * Date: 2020-10-06
 * File Name: MyTransitiveClosure.java
 * Author: Adam Bergman
 */
package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.DirectedGraph;
import ab224qr_assign3.graphs.Node;
import ab224qr_assign3.graphs.TransitiveClosure;

import java.util.*;

/**
 * Class Description: An algorithm that computes the
 * transitive closure for graphs
 * @version 1.0
 * @author Adam Bergman
 */
public class MyTransitiveClosure<E> implements TransitiveClosure<E> {
    /**
     * Computes the closure from nodes in the graph
     * @param dg directed graph
     * @return closures
     */
    @Override
    public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph<E> dg) {
        if (dg == null) {
            throw new NullPointerException();
        }

        Map<Node<E>, Collection<Node<E>>> closures = new LinkedHashMap<>();
        MyDFS<E> dfs = new MyDFS<>();

        for (Node<E> node : dg) {
            List<Node<E>> nodes = dfs.dfs(dg, node);
            closures.put(node, nodes);
        }

        return closures;
    }
}
