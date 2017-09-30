package ru.job4j.collections.iterator;

import java.util.Iterator;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class EvenIt implements Iterator {
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

    /**
     * Метод проверяет есть в массиве еше четные значения.
     * @return
     */
    @Override
    public boolean hasNext() {
        boolean found = false;
        if (index < numbers.length) {
            for (int i = index; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    /**
     * Метод возвращает текужее значение и передвигает каретку на 1 шаг.
     * @return значение из мссива.
     */
    @Override
    public Object next() {
        int value = 0;
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                value = numbers[i];
                index = i;
                index++;
                break;
            }
        }
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                index = i;
                break;
            }
        }
        return value;
    }
}