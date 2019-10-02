package ru.job4j.calculator.input;

import ru.job4j.calculator.Work;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private Set<String> operations = new HashSet<>(Arrays.asList("+", "-", "/", "*"));

    public boolean itsOperation(String op, Work work) {
        if (op.equals("off")) {
            work.stop();
        }
        return this.operations.contains(op);
    }
}
