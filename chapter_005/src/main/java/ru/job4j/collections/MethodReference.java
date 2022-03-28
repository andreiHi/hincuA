package ru.job4j.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReference {

    public void applyByInstance() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello");
        final Consumer<String> consumerByInstance = this::consumerByInstance;
        consumerByInstance.accept("instance method");
    }
    public static void applyByClass() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello");
        Consumer<String> consumerByClass = MethodReference::consumerByClass;
        consumerByClass.accept("static method");
    }

    private void consumerByInstance(String input) {
        System.out.print("From instance: " + input);
    }

    private static void consumerByClass(String input) {
        System.out.print("From class: " + input);

    }

    public static void main(String[] args) {
        MethodReference.applyByClass();
        new MethodReference().applyByInstance();

        calc(Arrays.asList(1, 2, 3, 1, 2)).entrySet().forEach(e -> System.out.println(e.getKey() +"  " + e.getValue()));
    }
    int a;
    public void ex() {
        int d;
        a++;
    }

    public static Map<Integer, Long> calc(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }
}
