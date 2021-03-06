package ru.job4j;

/**
 * Блок инициализации цикла выполняется первым и только один раз. Поэтому первым будет выведен символ 'A', и больше этот символ выводиться не будет.
 *    На каждой итерации цикла сначала выполняется проверка условия ('B'), затем выполняется тело цикла ('D'), затем - блок модификации переменных ('C').
 *    Поскольку выполняется две итерации цикла, то эти символы ('BDC') будут выведены дважды.
 *    Когда условие продолжения цикла становится ложным, цикл прекращает свою работу. Поэтому последним будет выведен символ 'B'.
 */

public class Test {

    static boolean foo(char c) {
        System.out.print(c);
        return true;
    }
    public static void main(String[] args) {
        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }
}
