package ru.job4j.builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class PizzaTest {

    @Test
    public void name() {
        new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .build();
    }

    @Test
    public void name2() {
        new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .addTopping(Pizza.Topping.ONION)
                .sauceInside()
                .build();
    }
}