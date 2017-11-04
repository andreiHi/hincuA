package ru.job4j.collections.frogway;

import java.util.ArrayList;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Way {


    public void move(int[][] array, int x, int y, int xX, int yY){
        int n = array[y][x];
        y = y + yY;
        x = x + xX;
        if (x > 15) {
            x = x - 16;
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
    public void start(int x, int y, int yF, int xF) {
        int[][] array = new int[10][16];
        Pair[][] array2 = new Pair[10][16];
        //array[8 - 1][5 - 1] = -1;
       // array[9 - 1][14 - 1] = -1;
     //   move(array, x - 1, y - 1,0, 0);
        move2(array2, x - 1, y - 1,0, 0);
        Pair n = array2[10 - 1][9 - 1];
        System.out.println(n);
        System.out.println(array.length);
        System.out.println(array[0].length);
        for (int i = array.length - 1; i  > -1; i--) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
       // sb = result(9,8, array);
        moveRevers(8, 9, array, 0, 0, sb);
       System.out.println( sb);
    }
    public void  moveRevers( int x, int y, int [][]array, int xX, int yY, StringBuilder sb) {
        int steps = array[y][x];
        y = y + yY;
        x = x + xX;
        if (x < 0) {
            x = x + 16;
        }
        if (9 < y || y < 0) {
            return;
        }
        int steps2 = steps - array[y][x];
        if (steps == 1) {

            return;
        }
        if (steps2 > 1 ) {
            return;
        }
        if (steps2 < 0) {
            return;
        }

        sb.append(y).append(":").append(x).append(" ");

       moveRevers(x, y, array,-3, 0, sb);
       moveRevers(x, y, array, -1, -2, sb);
       moveRevers(x, y, array,-1, 2, sb);
       moveRevers(x, y, array,-2, 1, sb);
       moveRevers(x, y, array,-2, -1, sb);
    }
    public void move2(Pair[][] array, int x, int y, int xX, int yY) {
        Pair pair = array[y][x];
        if (pair == null) {
            pair = new Pair(x, y,0);
            array[y][x] = pair;
        }
        int n = pair.getValue();
        x = x + xX;
        y = y + yY;
        if (x > 15) {
            x = x - 16;
        }
        if (y > 9 || y < 0) {
            return;
        }
        Pair pair1 = array[y][x];
        if (pair1 == null) {
            pair1 = new Pair(x, y, 0, pair);
            array[y][x] = pair1;
        }
        if (pair1.getValue() == -1) {
            return;
        }
        if (pair1.getValue() > 0 && pair1.getValue() < n) {
            return;
        }
        pair1.setValue(n + 1);
        pair1.setPrev(pair);

        move2(array, x, y, 3, 0);
        move2(array, x, y, 1, -2);
        move2(array, x, y, 1, 2);
        move2(array, x, y, 2, 1);
        move2(array, x, y, 2, -1);

    }
}
