package ru.job4j.univer;

import java.util.Comparator;
import java.util.List;
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
     * @param list list of students.
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
}
