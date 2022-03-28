package ru.job4j.testtask;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int i = 0; i < number.length; i++) {
            int num = i;
            int sum = total;
            total = add(
                    () ->  sum + num
            );
        }

        System.out.println(total);
    }

    private static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }

}


class Dto {
    boolean exists;

    public Dto(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
