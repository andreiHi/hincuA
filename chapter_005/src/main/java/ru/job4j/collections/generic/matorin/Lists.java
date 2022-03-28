package ru.job4j.collections.generic.matorin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lists {
    public void lists() {
        List<? extends Number> numbers; // нужно смотреть к чему можно приравнять ссылку этого типа
        numbers = new ArrayList<Long>();
        //numbers.add(1L);
        numbers = new ArrayList<Number>();
        numbers = new ArrayList<Integer>();
        numbers = new ArrayList<Double>();
        numbers.add(null);

        List<? super Number> nums = new ArrayList<Object>();
    }

    /**
     * из такой структуры можно только читать
     *  а добавить только null так как не известна реализация и в список Integer может быть передан Long
     * @param numbers
     */
    public void process(List<? extends Number> numbers) { //
        numbers.add(null); //
    }

    public static <T> T max(List<? extends  T> collection, Comparator<? super T> comparator) {
        Collections.sort(collection, comparator);
        return collection.get(0);
    }
}
