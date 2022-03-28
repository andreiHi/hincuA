package ru.job4j.profession;

import java.util.HashMap;

public abstract class SampleParent {
    int age = 55;
    public void method1() {
        System.out.println("SampleParent.method1");
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        method2();
    }
//
    public abstract Object method2();
}
