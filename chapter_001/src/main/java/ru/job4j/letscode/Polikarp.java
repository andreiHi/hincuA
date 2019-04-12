package ru.job4j.letscode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Polikarp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int con = sc.nextInt();
        AtomicInteger in = new AtomicInteger(0);
        IntStream.range(0, con)
                .mapToObj(i -> sc.nextInt())
                .sorted()
                .peek(i -> {
                    if (in.get() < i) {
                        in.incrementAndGet();
                    }
                }).collect(Collectors.toList());
        System.out.println(in.get());
    }
}
