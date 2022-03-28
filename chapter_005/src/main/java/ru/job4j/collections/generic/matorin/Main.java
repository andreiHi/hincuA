package ru.job4j.collections.generic.matorin;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
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
        //Class<Integer> cln = i.getClass();
        //System.out.println(cln);
       //compile error
        Class<? extends Integer> cl = i.getClass();
        System.out.println(cl);

        Person person = new Person();
        Stream.of(person.getClass().getDeclaredMethods()).forEach(System.out :: println);

        Method m1 = Person.class.getDeclaredMethod("compareTo", Person.class);
        Method m2 = Person.class.getDeclaredMethod("compareTo", Object.class);

        System.out.println(m1.isSynthetic());
        System.out.println(m2.isSynthetic());  // проверяет был ли данный метод сгенерирован компилятором
        System.out.println(m2.isBridge());

        System.out.println(new Date(1608731750L * 1000));

    }

    public void run(List<String> ... lists) { // Possible heap pollution from parameterized vararg type

    }
    public static List<Integer> frequencySort(List<Integer> arr) {
        // Write your code here...
        arr.sort(Comparator.naturalOrder());
        return arr;
    }
    public static Integer collect(List<Integer> list) {
        return list.stream()
                .reduce(0, (a, b) -> a * 5);
    }
}
