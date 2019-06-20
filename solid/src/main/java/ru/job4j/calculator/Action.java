package ru.job4j.calculator;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public interface Action {

     Double calculate(String operation, double first);
     Double calculate(String operation, double first, double second);
}
