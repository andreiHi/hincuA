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


    public void move(int[][] array, int x, int y, int xX, int yY){
        int n = array[y][x];
        y = y + yY;
        x = x + xX;
        if (x > 15) {
            x = x - 15;
        }
        if (y > 9 || y < 0) {
            return;
        }
        if (array[y][x] == -1) {
            return;
        }
        if (array[y][x] > 0 && array[y][x] < n) {
            return;
        }
        array[y][x] = n + 1;

        move(array, x, y, 3, 0);
        move(array, x, y, 1, -2);
        move(array, x, y, 1, 2);
        move(array, x, y, 2, 1);
        move(array, x, y, 2, -1);

    }
    public void srart() {
        int[][] array = new int[10][16];
        array[8-1][5-1] = -1;
        array[9-1][14-1] = -1;
        move(array, 11-1, 7-1,0, 0);
        int n = array[8][9];
        System.out.println(n);
        for (int i =0; i < array.length; i++) {
            for (int j =0; j < array[i].length; j++) {
                System.out.print(array[i][j]+ " ");
            }
            System.out.println();
        }
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