package ru.job4j.collections.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *Дерево.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 18.10.17;
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> node;
    private int size;

    public int getSize() {
        return size;
    }

    public Tree() {

    }
    private class Node<E> {
        private E value;
        private List<Node<E>> children;

        private Node( E value) {
            this.value = value;
        }

        private void addFirstChild(E children) {
            this.children = new ArrayList<>();
            this.children.add(new Node<E>(children));
        }
    }
    @Override
    public boolean add(E parent, E child) {
        boolean sucses = false;
        if (this.node == null) {
            this.node = new Node<>(parent);
            size++;
            this.node.addFirstChild(child);
            sucses = true;
            size++;
        } else {
            if (!contains(node.children, child) && ! node.value.equals(child)) {
                if (node.value.equals(parent)) {
                    node.children.add(new Node<>(child));
                    sucses = true;
                } else {
                    sucses = addChild(node.children, parent, child);
                }
                size++;
            }
        }
        return sucses;
    }
    private boolean addChild(List<Node<E>> list, E parent, E child) {
        boolean add = false;
        for (Node<E> node : list) {
            if (node.value.equals(parent)) {
                if (node.children == null) {
                    node.addFirstChild(child);
                    add = true;
                } else {
                    node.children.add(new Node<>(child));
                    add =  true;
                }
            } else if (node.children != null) {
                add = addChild(node.children, parent, child);
            }
        }
        return add;
    }
    private boolean contains(List<Node<E>> list, E child) {
        boolean found = false;
        for (Node<E> node : list) {
            if (node.value.equals(child)) {
                found = true;
                break;
            }
            if (node.children != null) {
                found = contains(node.children, child);
            }
        }
        return found;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pozition = 0;
            Node<E> node;
            @Override
            public boolean hasNext() {
                return pozition++ < size;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
