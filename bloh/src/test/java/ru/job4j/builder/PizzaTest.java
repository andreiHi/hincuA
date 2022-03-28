package ru.job4j.builder;

import org.junit.Test;

import java.io.File;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static ru.job4j.builder.Pizza.Topping.*;

public class PizzaTest {

    @Test
    public void name() {
        new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(SAUSAGE)
                .addTopping(ONION)
                .build();
    }

    @Test
    public void name2() {

        new Calzone.Builder()
                .addTopping(HAM)
                .addTopping(ONION)
                .sauceInside()
                .build();
    }

}
