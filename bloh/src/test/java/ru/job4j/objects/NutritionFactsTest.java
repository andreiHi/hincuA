package ru.job4j.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class NutritionFactsTest {
    @Test
    public void example() {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();
    }
}