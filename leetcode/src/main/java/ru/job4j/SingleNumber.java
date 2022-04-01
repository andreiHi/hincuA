package ru.job4j;

public class SingleNumber {

    /**
       xor позволяет убрать дубликаты так как
     0101 xor
     0101
     ----
     0000   xor возвращает 1 только тогда когда складываемые элементы разные
     true ^ false = true
     false ^ true = true
     true ^ true = false
     false ^ false = false
     */
    public int singleNumber(int[] nums) {
        int mask = 0;
        for (int num : nums) {
            mask ^= num;
        }
        return mask;
    }
}
