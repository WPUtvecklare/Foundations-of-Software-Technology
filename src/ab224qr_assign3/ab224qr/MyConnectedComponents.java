/*
 * Date: 2020-10-06
 * File Name: MyConnectedComponents.java
 * Author: Adam Bergman
 */
package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.ConnectedComponents;
import ab224qr_assign3.graphs.DirectedGraph;
import ab224qr_assign3.graphs.Node;

import java.util.*;

/**
 * Class Description: An algorithm that computes the
 * connected components in a graph
 * @version 1.0
 * @author Adam Bergman
 */
public class MyConnectedComponents<E> implements ConnectedComponents<E> {

    /**
     * Adds all components that are connected to each other in the
     * provided graph
     * @param dg graph
     * @return connected components
     */
    @Override
    public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg) {
        if (dg == null) {
            throw new NullPointerException();
        }

        Collection<Collection<Node<E>>> components = new LinkedHashSet<>();
        Set<Node<E>> visited = new LinkedHashSet<>();
        MyDFS<E> dfs = new MyDFS<>();

        for (Node<E> node : dg) {
            if (!visited.contains(node)) {
                List<Node<E>> nodes = dfs.dfs(dg, node);
                visited.addAll(nodes);

                Set<Node<E>> component  = new HashSet<>(nodes);

                for (Collection<Node<E>> c : components) {
                    // Checks if there are some components that are the same within
                    // the collections, in that case they're merged together
                    if (!Collections.disjoint(c, component)) {
                        c.addAll(component);
                        component.clear();
                    }
                }

                if (!component.isEmpty()) {
                    components.add(component);
                }
            }
        }

        return components;
    }
}
