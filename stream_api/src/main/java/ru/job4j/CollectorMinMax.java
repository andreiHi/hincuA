package ru.job4j;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorMinMax {

    public static <T, R> Collector<T, ?, Optional<R>> minMax(Comparator<? super T> cmp,
                                                             BiFunction<? super T, ? super T, ? extends R>finisher) {
        class Acc {
            T min;
            T max;
            boolean present;

            void add(T t) {
                if (present) {
                    if (cmp.compare(t, min) < 0) min = t;
                    if (cmp.compare(t, max) > 0) max = t;
                } else {
                    min = max = t;
                    present = true;
                }
            }
            Acc combine(Acc other) {
                if (!other.present) return this;
                if (!present) return other;
                if (cmp.compare(other.min, min) < 0) min = other.min;
                if (cmp.compare(other.max, max) > 0) min = other.max;
                return this;
            }
        }
        return Collector.of(
                Acc::new,
                Acc::add,
                Acc::combine,
                acc -> acc.present ? Optional.of(finisher.apply(acc.min, acc.max))
                        : Optional.empty()
        );
    }

    public static Collector<Integer, ?, Optional<Integer>> sumAvg() {
        class Acc {
            Integer sum = 0;
            Integer count = 0;

            void add(Integer el) {
                this.sum = this.sum + el;
                this.count++;
            }

            Acc combine(Acc other) {
                sum = other.sum + sum;
                count = other.count + count;
                return this;
            }
        }
        return Collector.of(
                Acc::new,
                Acc::add,
                Acc::combine,
                acc -> {
                    int value = acc.count != 0 ? acc.sum / acc.count : 0;
                    return  Optional.of(value);
                }
        );
    }

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4)
                .collect(sumAvg()).ifPresent(System.out::println);

        List<?> list = new ArrayList<>();
        //list.add(123);
        List<? extends Number> list1 = new ArrayList<>();
         //list1.add(2D);
        List<? super Number> num = new ArrayList<>();
        num.add(2D);

//        Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight")
//                .collect(minMax(Comparator.comparingInt(String::length), (min, max) -> min + "|" + max))
//                .ifPresent(System.out::println);
        Optional<String> s = Optional.of("1232").map(d -> d.toLowerCase(Locale.ROOT));
        Optional<String> s1 = Optional.of("12121").flatMap(w -> Optional.of(w.toLowerCase(Locale.ROOT)));

    }
}
