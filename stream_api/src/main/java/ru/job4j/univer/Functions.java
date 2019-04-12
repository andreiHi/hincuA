package ru.job4j.univer;

import antlr.collections.impl.IntRange;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Functions {

    /*
        Функции первого класса.
        ЯП имеет функции первого класса, если он рассматривает функции как объекты первого класса(поддерживает
        передачу функции в качестве аргументов другим функциям, возврат их как результат других функций,
        присваивание их переменным)
     */
    public int sum(int x, int y) {
        return x + y;
    }
    private BiFunction<Integer, Integer, Integer> biFunction = this::sum;
    private BinaryOperator<Integer> operator = this::sum;
    private IntBinaryOperator sum = this::sum;

    Comparator<Integer> comparator = this::sum;

    /*
    Функции высшего порядка
     - функция, которая принимает в качестве аргумента другие функции
     - функция, возвращающая другую функцию в качестве результата
     */
    public Runnable decorate(Runnable runnable) {
        return () -> {
            long time = System.currentTimeMillis();
            try {
                runnable.run();
            } finally {
                System.out.println("Exec time :" + (System.currentTimeMillis() - time));
            }
        };
    }

    /*
        Частичное применение функции
        Это процесс фиксации части аргументов, который из одной функции создает другую
        функцию с меньшим количеством аргументов

        final IntBinaryOperator biSum = partialSum(1); // выносим передачу одного параметра
        final int sum1 = biSum.applyAsInt(3, 2);
        final int sum2 = biSum.applyAsInt(4, 5);
     */
    public  int sum(int x, int y, int z) {
        return  x + y + z;
    }
    public  IntBinaryOperator partialSum(int x) {
        return (y, z) -> sum(x, y, z);
    }

    /*
    Каррирование
    Это преобразование функции от многих аргументов в функцию, берущую свои  аргументы по одному
     final int sum = function.currySum().apply(1).apply(2).applyAsInt(3);
     */
    public IntFunction<IntFunction<IntUnaryOperator>> currySum() {
        return x -> y -> z -> sum(x, y, z);
    }

    public static void main(String[] args) {
        final int sum1 = new Random()
                .ints(1, 20 + 1)
                .distinct().limit(5)
                .peek(System.out::println)
                .sum();

        Functions function = new Functions();
        final IntBinaryOperator operator = function.partialSum(2);
        System.out.println(operator.applyAsInt(5, 3));

        final int sum = function.currySum().apply(1).apply(2).applyAsInt(2);
        System.out.println(sum);
    }

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        return IntStream.range(start, end)
                .mapToObj(idx -> func.apply((double) idx))
                .collect(Collectors.toList());

    }
    public Map<Character, TreeSet<String>> sort(String line) {
        return Arrays.stream(line.split(" "))
                .distinct()
                .collect(Collectors.
                        groupingBy(s -> s.charAt(0),
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(String::length)))));
    }
}
