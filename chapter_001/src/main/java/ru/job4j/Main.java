package ru.job4j;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 18.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Main {

    private String variable;
    public Main() {
        printVariable();
    }

    protected void printVariable() {
        variable = "variable is initialized in Main Class";
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        B b = new B();
    }
}

class B extends Main {
    private String variable = null;

    public B() {
        System.out.println("variable value = " + variable);
    }

    protected void printVariable() {
        variable = "variable is initialized in B Class";
    }
}

