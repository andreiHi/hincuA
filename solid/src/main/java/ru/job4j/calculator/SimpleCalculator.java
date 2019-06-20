package ru.job4j.calculator;

import ru.job4j.calculator.calcs.Calculator;
import ru.job4j.calculator.input.ConsoleInput;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleCalculator implements Action {

    private Calculator calculator;
    private Map<String, BiConsumer<Double, Double>> map = new HashMap<>();

    public SimpleCalculator(Calculator calculator) {
        this.calculator = calculator;
        init();
    }

    private void init() {
        map.put("+", this::add);
        map.put("-", this::subtract);
        map.put("/", this::div);
        map.put("*", this::multiple);
    }

    private void add(Double a, Double b) {
        this.calculator.add(a, b);
    }

    private void subtract(Double a, Double b) {
        this.calculator.subtract(a, b);
    }

    private void div(Double a, Double b) {
        this.calculator.div(a, b);
    }

    private void multiple(Double a, Double b) {
        this.calculator.multiple(a, b);
    }

    @Override
    public Double calculate(String operation, double first) {
        this.map.getOrDefault(operation, this::unknown).accept(
                this.calculator.getResult(), first
        );
        return this.calculator.getResult();
    }

    @Override
    public Double calculate(String operation, double first, double second) {
        this.map.getOrDefault(operation, this::unknown).accept(
                first, second
        );
        return this.calculator.getResult();
    }

    public void unknown(double a, double b) {
        System.out.println("Unknown operation");
    }
}
