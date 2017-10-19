package ru.job4j.collections.tree;

import java.util.*;

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
        boolean success = false;
        if (this.node == null) {
            this.node = new Node<>(parent);
            size++;
            this.node.addFirstChild(child);
            success = true;
            size++;
        } else {
            if (!contains(node.children, child) &&  node.value.compareTo(child)!= 0) {
                if (node.value.compareTo(parent) == 0) {
                    node.children.add(new Node<>(child));
                    success = true;
                } else {
                    success = addChild(node.children, parent, child);
                }
                size++;
            }
        }
        return success;
    }
    private boolean addChild(List<Node<E>> list, E parent, E child) {
        boolean add = false;
        for (Node<E> node : list) {
            if (node.value.compareTo(parent) == 0) {
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
    public boolean add1(E parent, E child) {
        boolean success = false;
        if (parent.compareTo(child) != 0) {
            if (this.node == null) {
                this.node = new Node<>(parent);
                size++;
                this.node.addFirstChild(child);
                success = true;
                size++;
            } else {
                if (node.value.compareTo(child) != 0) {
                    if (node.value.compareTo(parent) == 0) {
                        boolean con = false;
                        for (Node<E> n : node.children) {
                            if (n.value.compareTo(child) == 0) {
                                con = true;
                                break;
                            }
                        }
                        if (!con) {
                        node.children.add(new Node<>(child));
                        success = true;}
                    } else {
                        success = addIfNotContains(node.children, parent, child);
                    }
                    size++;
                }
            }
        }
        return success;
    }

    public boolean addIfNotContains(List<Node<E>> list,E parent, E child) {
        boolean found = false;
        Node<E> nodeParent = null;
        for (Node<E> n : list) {
            if (n.value.compareTo(parent) == 0) {
                nodeParent = n;
            }
            if (n.value.compareTo(child) == 0) {
                found = true;
                break;
            }
            if (n.children != null) {
                found = addIfNotContains(n.children, parent, child);
            }
        }
        if (!found && nodeParent != null) {
            if (nodeParent.children == null) {
                nodeParent.addFirstChild(child);
            } else {
                nodeParent.children.add(new Node<>(child));
            }
            found = true;
        }
        return  found;
    }
    @Override
    public Iterator<E> iterator() {
        Node<E>n = node;
        return new Iterator<E>() {
            List<Node<E>> list = new ArrayList<>();
            Queue<Node<E>> queue = new ArrayDeque<>();
            int position = 0;
            Node<E> val;
            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (list.isEmpty()) {
                        list.add(n);
                        if (n.children != null) {
                            queue.addAll(n.children);
                        }
                       val = n;
                    } else {
                        val = queue.poll();
                        list.add(val);
                        if (val.children != null) {
                            queue.addAll(val.children);
                        }
                    }
                } else {
                    throw new NoSuchElementException();
                }
                position++;
                return val.value;
            }
        };
    }
}
