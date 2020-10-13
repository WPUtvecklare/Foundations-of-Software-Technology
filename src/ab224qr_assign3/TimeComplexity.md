# Time Complexity

## Depth-First Search as implemented in ab224qr.MyDFS.dfs(DirectedGraph<E> graph)

### Code:

```java
public List<Node<E>> dfs(DirectedGraph<E> graph) {
        if (graph == null) { // O(1)
            throw new NullPointerException();
        }

        Set<Node<E>> visited = new LinkedHashSet<>(); // O(1)

        if (graph.headCount() == 0) { // O(1)
            Node<E> firstNode = graph.getNodeFor(graph.allItems().get(0)); // O(1)
            doDFS(firstNode, visited); // O(1)
        } else {
            graph.heads().forEachRemaining(node -> { // O(N)
                doDFS(node, visited)); // O(S)
            }
        }

        return new ArrayList<>(visited); // O(1)
}

private void doDFS(Node<E> node, Set<Node<E>> visited) {
        if (!visited.contains(node)) { // O(1)
            node.num = visited.size(); // O(1)
            visited.add(node); // O(1)

            Iterator<Node<E>> successors = node.succsOf(); // O(1)

            while (successors.hasNext()) { // O(S)
                Node<E> successor = successors.next(); // O(1)

                if (!visited.contains(successor)) { // O(1)
                    doDFS(successor, visited); // O(1)
                }
            }
        }
}
```

### Description:

The algorithm loops through all heads within the graph as a worst-case scenario ( O(N) ), otherwise it takes
the first node, and calls on doDFS ( O(1) ). Inside doDFS it loops through the current node's successors ( O(S) )
and calls itself recursively for each successor. 
Since both methods contains constants and infinite, the constants can be removed, which means 
that we have O(N) in dfs and O(S) in doDFS.

The time complexity is written as O(N + S) where N is the amount of nodes and S is the amount of successors.

---

## Breadth-First Search as implemented in ab224qr.MyBFS.bfs(DirectedGraph<E> graph)

### Code:

```java
public List<Node<E>> bfs(DirectedGraph<E> graph) {
        if (graph == null) { // O(1)
            throw new NullPointerException();
        }

        Queue<Node<E>> toVisit = new LinkedList<>(); // O(1)

        if (graph.headCount() == 0) { // O(1)
            Node<E> firstNode = graph.getNodeFor(graph.allItems().get(0)); // O(1)
            toVisit.add(firstNode); // O(1)
        } else {
            graph.heads().forEachRemaining(toVisit::add); // O(N)
        }

        return doBFS(toVisit);
}

private List<Node<E>> doBFS(Queue<Node<E>> toVisit) {
        Set<Node<E>> visited = new LinkedHashSet<>(); // O(1)

        do {
            Node<E> node = toVisit.poll(); // O(1)

            if (!visited.contains(node)) { // O(1)
                visited.add(node); // O(1)
                node.num = visited.size(); // O(1)

                node.succsOf().forEachRemaining(successor -> { // O(S)
                    if (!visited.contains(successor)) { // O(1)
                        toVisit.add(successor); // O(1)
                    }
                });
            }

        } while (!toVisit.isEmpty()); // O(N)

        return new ArrayList<>(visited);
    }
```

### Description:

In bfs, we have infinite in the case of heads in the graph, which means that we eliminate the constants
and write it as O(N). If there are no heads, it's written as O(1).

For doBFS, which is called together with the nodes, there are two loops, one loops through the 
successors and one loops through the nodes to visit, which means that we get the same time complexity
as we did in dfs, which is O(N + S). 

---

## Transitive Closure as implemented in ab224qr.MyTransitiveClosure.computeClosure(DirectedGraph<E> graph)

### Code:

```java
public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph<E> dg) {
        if (dg == null) { // O(1)
            throw new NullPointerException();
        }

        Map<Node<E>, Collection<Node<E>>> closures = new LinkedHashMap<>(); // O(1)
        MyDFS<E> dfs = new MyDFS<>(); // O(1)

        for (Node<E> node : dg) { // O(N)
            List<Node<E>> nodes = dfs.dfs(dg, node); // O(N + S)
            closures.put(node, nodes); // O(1)
        }

        return closures;
}
```

### Description:

computeClosure loops through the nodes in the graph, giving us O(N), and for each node in the graph, it
calls on dfs, which we already know has a time complexity of O(N + S). The rest are constants and therefore
eliminated.

The time complexity for computing closures: O(N(N + S)), which is written as O(N² + NS).

---

## Connected Components as implemented in ab224qr.MyConnectedComponents.computeComponents(DirectedGraph<E> graph)

### Code:

```java
    public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg) {
        if (dg == null) { // O(1)
            throw new NullPointerException();
        }

        Collection<Collection<Node<E>>> components = new LinkedHashSet<>(); // O(1)
        Set<Node<E>> visited = new LinkedHashSet<>(); // O(1)
        MyDFS<E> dfs = new MyDFS<>(); // O(1)

        for (Node<E> node : dg) { // O(N)
            if (!visited.contains(node)) { // O(1) -- because of HashSet
                List<Node<E>> nodes = dfs.dfs(dg, node); // O(N + S)
                visited.addAll(nodes); // O(N)

                Set<Node<E>> component  = new HashSet<>(nodes); // O(1)

                for (Collection<Node<E>> c : components) { // O(C)
                    if (!Collections.disjoint(c, component)) { // O(C)
                        c.addAll(component); // O(C) -- (Assumption)
                        component.clear(); // O(1)  
                    }
                }

                if (!component.isEmpty()) { // O(1)
                    components.add(component); // O(1)
                }
            }
        }

        return components;
}
```

### Description:

The algorithm iterates over all nodes in the graph ( O(N) ), performing depth-first search 
on each node ( O(N + S) ), then loops through all the visited nodes and adds the result 
from dfs ( O(N) ), after that, it loops through the components ( O(C) ) and calls disjoint 
( O(C) ), which iterates through the collection and adds the component to the collection 
if they have no elements in common ( O(C) ).

The time complexity could be written as: O(N^2 + (N+S) + (C^3)). Although,
since the nested C-loop will be iterated N amount of times, another result
could be:

O( (N + S) + N + C(C(C)) ) --> O(N² + NS + N² + NC^3).

---

## Resources
https://gist.github.com/psayre23/c30a821239f4818b0709
http://bigocheatsheet.com/
https://mymoodle.lnu.se/pluginfile.php/6314765/mod_resource/content/1/2DV600_Lecture7.pdf