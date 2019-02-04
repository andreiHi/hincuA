package ru.job4j.collections.iterator;

import antlr.collections.impl.IntRange;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class EvenIt implements Iterable<Integer> {
    /**
     * Конструктор.
     * @param numbers массив интов.
     */
    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * массив значений.
     */
    private final int[]numbers;
    /**
     * Позиция в массиве.
     */
    private int index = 0;



    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            /**
             * Метод проверяет есть в массиве еше четные значения.
             * @return
             */
            @Override
            public boolean hasNext() {
                index += (int) IntStream.range(index, numbers.length)
                        .takeWhile(i -> numbers[i] % 2 != 0).count();
                return index < numbers.length;
            }

            /**
             * Метод возвращает текужее значение и передвигает каретку на 1 шаг.
             * @return значение из мссива.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return numbers[index++];
            }
        };
    }
}
