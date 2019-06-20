package ru.job4j.calculator.calcs;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Calculator {

    private double result;

    public void add(double a, double b) {
        this.result = a + b;
    }
    public void subtract(double a, double b) {
        this.result = a - b;
    }

    public void div(double a, double b) {
        this.result = a / b;
    }
    public void multiple(double a, double b) {
        this.result = a * b;
    }

    public double getResult() {
        return result;
    }
}
