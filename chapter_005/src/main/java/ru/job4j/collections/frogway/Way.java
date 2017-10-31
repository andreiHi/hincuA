package ru.job4j.collections.frogway;

import java.util.ArrayList;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Way {
    private Items frog;
    private Items endWay;
    private int countOfTrees;
    ArrayList<Items> trees = new ArrayList<>(countOfTrees);
    public void itemsToStart() {
        Items tree1 = new Items("Ёлка", 9, 14);
        Items tree2 = new Items("Ёлка2", 8, 5);
        trees.add(tree1);
        trees.add(tree2);
        frog = new Items("Гушка", 7, 11);
        endWay = new Items("Финиш", 10, 9);
    }

    public void move(){

    }

    public boolean positionIsEmpty(Position p) {
        boolean f = true;
        for (Items i : trees) {
            if (p.equals(i.getPosition())) {
                f = false;
                break;
            }
        }
        return f;
    }
}