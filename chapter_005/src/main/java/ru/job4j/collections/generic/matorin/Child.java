package ru.job4j.collections.generic.matorin;

import java.util.ArrayList;
import java.util.List;

public class Child extends Parent {

     int a = 20;
    @Override
    public Integer run(String s) {
        return 2;
    }

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.a);
        List<? super Number>list = new ArrayList<>();
        list.add(1);
        list.add(0.1);
        final Double x = (Double) list.get(0);
        System.out.println(x);
        final Double x1 = (Double) list.get(1);
        System.out.println(x1);
    }
}
