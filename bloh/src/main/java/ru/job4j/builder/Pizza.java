package ru.job4j.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

    public enum Topping{ HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }

    private final Set<Topping> toppings;

    protected Pizza(Builder<?> builder) {
        this.toppings = builder.toppings.clone();
    }
    abstract static class Builder<T extends Builder<T>> {
        private EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();
        // Подклассы должны перекрывать этот метод, возвращая "себя"
        protected  abstract T self();
    }

}
