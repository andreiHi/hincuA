package ru.job4j;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Test2  {
    private String name;
    private String hello;
    public Test2(String name) {
        this.name = name;
    }

    public String getHello() {
        return hello;
    }

    public static void main(String[] args) {

        System.out.println((new Test2("name").getHello() instanceof String));
    }
}
