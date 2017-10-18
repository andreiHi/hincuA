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

    public Tree() {

    }

    class Node<E> {
        private List<Node<E>> children;
        private E value;

        public Node( E value) {
            //this.children = new ArrayList<>();
            this.value = value;
        }

        public List<Node<E>> getChildren() {
            return children;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setChildren(E children) {
            this.children = new ArrayList<>();
            this.children.add(new Node<E>(children));
        }
    }
    @Override
    public boolean add(E parent, E child) {
        boolean sucses;
        if (this.node == null) {
            this.node = new Node<>(parent);
            this.node.setChildren(child);
            sucses = true;
        } else {
            if (node.getChildren() == null) {
                node.setChildren(child);
                sucses = true;
            } else {
                if (node.value.equals(parent)) {
                    if (!node.children.contains(child)) {
                        node.children.add(new Node<>(child));
                        sucses = true;
                    }
                } else {
                    sucses = addIfNotContains(node.getChildren(), parent, child);
                }
            }
        }
        return sucses;
    }
    private boolean addIfNotContains(List<Node<E>> list, E parent, E child) {
        for (Node<E> node : list) {
            if (node.value.equals(parent)) {
                if (node.children == null) {
                    node.setChildren(child);
                    return true;
                }
                if (!node.children.contains(child)) {
                    node.children.add(new Node<>(child));
                    return true;
                }
            } else if (node.getChildren() != null) {
                List<Node<E>> childrens = node.getChildren();
                addIfNotContains(childrens, parent, child);
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
