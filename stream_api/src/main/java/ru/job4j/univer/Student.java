package ru.job4j.univer;



/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Student implements Comparable<Student> {

    /**
     * Student name.
     */
    private String name;

    /**
     * Student scope.
     */
    private int scope;

    private int age;


    /**
     * Constructor student.
     *
     * @param name  student name.
     * @param scope student scope.
     */
    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    /**
     * Get name student.
     *
     * @return name student.
     */
    public String getName() {
        return name;
    }

    /**
     * Get scope student.
     *
     * @return scope.
     */
    public int getScope() {
        return scope;
    }

    public int getAge() {
        return age;
    }

    /**
     * Compare with other student.
     *
     * @param o other student.
     * @return result compare.
     */
    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.scope, o.scope);
    }

    /**
     * String representation of an objectю
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("Student{name=%s, scope=%d}", this.name, this.scope);
    }
}
