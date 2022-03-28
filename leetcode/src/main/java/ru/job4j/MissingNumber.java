package ru.job4j;

public class MissingNumber {

    public int missingNumber(int[] nums) {
        boolean[] array = new boolean [nums.length + 1];
        for (int n : nums) {
            array[n] = true;
        }
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Сумма арифмитической прогрессии
     * (a1 + an) * n / 2 = sum
     * Так как у нас последовательность начинается с 0 то
     * мы считаем сумму как будто последовательность целая и вычитаем сумму массива
     * разность есть искомое число
     * т.е
     * первое число 0 последнее это длина массива + 1и тогда сумма ариф. последовательности когда все цифры есть будет
     * (0 + arr.length + 1) *(arr.length + 1) / 2
     */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int sumWithOutNum = 0;
        for (int num : nums) {
            sumWithOutNum += num;
        }
        return n * (n + 1) / 2 - sumWithOutNum;
    }
}
