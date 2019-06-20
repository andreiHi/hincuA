package ru.job4j.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.calculator.input.ConsoleInput;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Work {
        private ConsoleInput input;
        private SimpleCalculator calculator;

    public Work(ConsoleInput input, SimpleCalculator calculator) {
        this.input = input;
        this.calculator = calculator;
    }
    public void start() {
        System.out.println("Enter exit to close.");


        while (true) {
            double v = input.readDouble();

        }

    }
}
