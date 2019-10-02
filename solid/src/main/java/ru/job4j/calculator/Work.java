package ru.job4j.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.calculator.calcs.Calculator;
import ru.job4j.calculator.input.ConsoleInput;
import ru.job4j.calculator.input.Validator;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Work {
        private ConsoleInput input;
        private SimpleCalculator calculator;
        private Validator validator = new Validator();
        private boolean work;

    public Work(ConsoleInput input, SimpleCalculator calculator) {
        this.input = input;
        this.calculator = calculator;
    }
    public void start() {
        System.out.println("Enter exit to close.");
        String op = "";
        while (!this.work) {
            String f = input.readAction();
            if (validator.itsOperation(f, this)) {
                double s = input.readDouble();
                Double calculate = calculator.calculate(f, s);
                System.out.println(calculate);
            } else {
                String ac = input.readAction();
                double s = input.readDouble();
                System.out.println(calculator.calculate(ac, Double.parseDouble(f), s));
            }
        }
    }

    public void stop() {
        this.work = true;
    }

    public static void main(String[] args) {
        new Work(new ConsoleInput(), new SimpleCalculator(new Calculator())).start();
    }
}
