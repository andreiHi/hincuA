package ru.job4j.univer;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class StudentUtil {
    /**
     * Get list of students by scope.
     *
     * @param list  list of students.
     * @param bound bound.
     * @return list of students by scope.
     */
    public List<Student> levelOf(List<Student> list, int bound) {
        return list.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.reverseOrder())
                .takeWhile(student -> student.getScope() > bound)
                .collect(Collectors.toList());
    }

    public List<Student> collectStudents(List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .distinct()
                .collect(Collectors.toList());
    }
    private Predicate<Student> olderThan(int age) {
        return student -> student.getAge() >= age;
    }

    private Predicate<Student> byName(String name) {
        return student -> name.equals(student.getName());
    }

    public void run(List<Student> students) {
        final List<Student> stud = collectStudents(students, olderThan(18));
        final List<Student> list = collectStudents(students, olderThan(25).and(byName("Vasea")));
        final List<Student> students1 = collectStudents(students, byName("Lena").negate().and(olderThan(18)));
    }

    public void run2(List<Student> students) {
        final Function<String, Predicate<Student>> byName = name -> student -> name.equals(student.getName());
        IntFunction<Predicate<Student>> olderThan = yars -> student -> student.getAge() >= yars;

        final List<Student> alex = collectStudents(students, byName.apply("Alex").and(olderThan(20)));
        collectStudents(students, byName.apply("Julia").and(olderThan.apply(18)));
    }
}
