package ru.job4j.calculator.input;

import java.util.Scanner;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class ConsoleInput {

    private Scanner sc = new Scanner(System.in);

    public double readDouble() {
        return Double.valueOf(sc.nextLine());
    }

    public String readAction() {
        return sc.nextLine();
    }
}
