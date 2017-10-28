package ru.job4j.collections.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Node Задача : написать метод который меняет значения в дереве с левого на правый.
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Node {
    private Node left;
    private Node right;
    private int value;

    public Node(int value) {
        this.value = value;
    }
    public void revers() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null && node.right != null) {
                Node temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left.left != null) {
                    queue.add(node.left.left);
                }
                if (node.right.right != null) {
                    queue.add(node.right.right);
                }
            } else if (node.left != null) {
                node.right = node.left;
                node.left = null;
                if (node.right.right != null) {
                    queue.add(node.right.right);
                }
                if (node)
            }
        }
    }
}
