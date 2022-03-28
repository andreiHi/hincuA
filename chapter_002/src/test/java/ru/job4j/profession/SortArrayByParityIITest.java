package ru.job4j.profession;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.*;

public class SortArrayByParityIITest {

    @Test
    public void test1() {
        int[] nums = new int[]{4, 2, 5, 7};
        var sort = new SortArrayByParityII();
        sort.sortArrayByParityII(nums);
        System.out.println(Arrays.toString(nums));
        Path of = Path.of("");
        boolean absolute = of.isAbsolute();
    }

    @Test
    public void name() {
        System.out.println(Arrays.asList(1, 5));
        Map<String, List<String>> errors = new HashMap<>();

        List<String> takeProfit = errors.computeIfAbsent("takeProfit", k -> new ArrayList<>());
        takeProfit.add("error 1");

        List<String> takeProfit2 = errors.computeIfAbsent("takeProfit", k -> new ArrayList<>());
        takeProfit2.add("error 1");
        System.out.println(12);

        List<Number> integers = new ArrayList<>();
        integers.add(new Integer(1));

        Number[] arr = new Number[10];
        arr[0] = new Integer(10);
    }
}
