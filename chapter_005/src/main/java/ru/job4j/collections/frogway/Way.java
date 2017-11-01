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
        array[8 - 1][5 - 1] = -1;
        array[9 - 1][14 - 1] = -1;
        move(array, x - 1, y - 1,0, 0);
        int n = array[10 - 1][9 - 1];
        System.out.println(n);
        System.out.println(array.length);
        System.out.println(array[0].length);
        for (int i = array.length - 1; i  > -1; i--) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = n-1; i > 1; i--) {

            int xX = -3;
            int yY = 0;
            if (i == array[yF + yY][xF + xX]) {
                yF = yF + yY;
                xF = xF + xX;
            }

        }
      //  StringBuilder sb = new StringBuilder();
      //  moveRevers(9, 8, array, 0, 0, sb);
       // System.out.println( sb);
    }
    public void  moveRevers( int x, int y, int [][]array, int xX, int yY, StringBuilder sb) {
        int steps = array[y][x];
        steps = steps - 1;
        y = y - yY;
        x = x - xX;
        if (x > 15) {
            x = x - 16;
        }
        if (y > 9 || y < 0) {
            return;
        }
        if (steps != array[y][x]) {
            return;
        }
        if (steps == 2) {
            return;
        }
        sb.append(y).append(":").append(x).append(" ");
       // steps = steps -1;
       moveRevers(x, y, array,-3, 0, sb);
       moveRevers(x, y, array,-1, -2, sb);
       moveRevers(x, y, array,-1, 2, sb);
       moveRevers(x, y, array,-2, 1, sb);
       moveRevers(x, y, array,-2, -1, sb);
    }
}
