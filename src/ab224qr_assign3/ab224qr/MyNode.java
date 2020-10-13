/*
 * Date: 2020-09-31
 * File Name: MyNode.java
 * Author: Adam Bergman
 */
package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Class description: Implementation of the abstract Node class
 * @param <E>
 * @author Adam Bergman
 */
public class MyNode<E> extends Node<E> {
    private final Set<Node<E>> successors;
    private final Set<Node<E>> predecessors;

    public MyNode(E item) {
        super(item);
        this.successors = new HashSet<>();
        this.predecessors = new HashSet<>();
    }

    /**
     * Checks if the successor contains the provided node
     * @param node to check against
     * @return true if it has, else false
     */
    @Override
    public boolean hasSucc(Node<E> node) {
        return successors.contains(node);
    }

    /**
     * Get the number of successors
     * @return node out-degree
     */
    @Override
    public int outDegree() {
        return successors.size();
    }

    /**
     * Get iterator over the successor nodes
     * @return successor node iterator
     */
    @Override
    public Iterator<Node<E>> succsOf() {
        return successors.iterator();
    }

    /**
     * Checks if the predecessor contains the provided node
     * @param node to check against
     * @return true if it has, else false
     */
    @Override
    public boolean hasPred(Node<E> node) {
        return predecessors.contains(node);
    }

    /**
     * Get the number of predecessors
     * @return node in-degree
     */
    @Override
    public int inDegree() {
        return predecessors.size();
    }

    /**
     * Get iterator over the predecessor nodes
     * @return predecessor node iterator
     */
    @Override
    public Iterator<Node<E>> predsOf() {
        return predecessors.iterator();
    }

    /**
     * Adds node to the successors
     * @param succ to add
     */
    @Override
    protected void addSucc(Node<E> succ) {
        successors.add(succ);
    }

    /**
     * Removes node from the successors
     * @param succ to remove
     */
    @Override
    protected void removeSucc(Node<E> succ) {
        successors.remove(succ);
    }

    /**
     * Adds node to the predecessors
     * @param pred to add
     */
    @Override
    protected void addPred(Node<E> pred) {
        predecessors.add(pred);
    }

    /**
     * Removes node from the predecessors
     * @param pred to remove
     */
    @Override
    protected void removePred(Node<E> pred) {
        predecessors.remove(pred);
    }

    /**
     * Removes all successors and predecessors
     */
    @Override
    protected void disconnect() {
        successors.clear();
        predecessors.clear();
    }
}
