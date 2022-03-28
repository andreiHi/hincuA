package ru.job4j.profession;

import java.util.*;

public class SampleChild extends SampleParent {
    int age = 42;

    @Override
    public String method2() {
        System.out.println();
        return "ne ";
    }

    public SampleChild() {
    }

    public SampleChild(SampleChild sampleChild) {
        this.age = sampleChild.age;
    }


    public static void main(String[] args) {
        Write write = new Write() {};
        WW w = new WW() {};
    }
}
abstract class WW {

}
