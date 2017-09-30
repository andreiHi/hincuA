package ru.job4j.collections.iterator;

import java.util.Iterator;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class PrimeIt implements Iterator {
    /**
     * Массив значений.
     */
    private final int[]numbers;
    /**
     * Позиция в массиве.
     */
    private int index = 0;

    /**
     * Конструктор класса.
     * @param numbers
     */
    public PrimeIt(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод проверяет есть ли в масиве еще простые числа.
     * @return true or false;
     */
    @Override
    public boolean hasNext() {
        boolean found = false;
        if (index < numbers.length) {
            for (int i = index; i < numbers.length; i++) {
                if (numbers[i] != 0) {
                    if (numbers[i] / numbers[i] == 1) {

                    }
                }
            }
        }
        return found;
    }

    /**
     * Метод возвращает текущее значение и переводит каретку на следующее значение.
     * @return
     */
    @Override
    public Object next() {
        return null;
    }
}
