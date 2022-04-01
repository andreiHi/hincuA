package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean [] arr = new boolean[nums.length];
        for (int num : nums) {
            arr[num - 1] = true;
        }
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i]) {
                integers.add(i + 1);
            }
        }
        return integers;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int pos = nums[i] - 1;
            if (nums[i] != nums[pos]) {
                int el = nums[i];
                nums[i] = nums[pos];
                nums[pos] = el;
            } else {
                i++;
            }
        }
        List<Integer> integers = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                integers.add(j + 1);
            }
        }
        return integers;
    }
}
