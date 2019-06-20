package ru.job4j.collections.generic.matorin;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        Integer i = 2456;
        Class<? extends Integer> cl = i.getClass();
      //   Class<Integer> cln = i.getClass();  compile error
        System.out.println(cl);

        Person person = new Person();
        Stream.of(person.getClass().getDeclaredMethods()).forEach(System.out :: println);

        Method m1 = Person.class.getDeclaredMethod("compareTo", Person.class);
        Method m2 = Person.class.getDeclaredMethod("compareTo", Object.class);

        System.out.println(m1.isSynthetic());
        System.out.println(m2.isSynthetic());  // проверяет был ли данный метод сгенертрован компилятором
        System.out.println(m2.isBridge());

    }

    public void run(List<String> ... lists) { // Possible heap pollution from parameterized vararg type

    }
}
