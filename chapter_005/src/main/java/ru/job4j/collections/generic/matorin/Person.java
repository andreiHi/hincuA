package ru.job4j.collections.generic.matorin;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Person implements Comparable<Person> {
    private String name;

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }

//    public int compareTo(Object o) {         в это будет скомпилирован метод выше
//        if (o instanceof Person) {
//            Person person = (Person)o;
//            return name.compareTo(person.name);
//        }
//        return 0;
//    }
}
