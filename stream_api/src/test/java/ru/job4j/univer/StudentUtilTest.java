package ru.job4j.univer;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class StudentUtilTest {

    @Test
    public void whenGetGroupStudentsThenGetListStudentsBasedOnScore() {
        List<Student> students = Arrays.asList(
                new Student("Kirill", 45), new Student("Bill", 77),
                new Student("Den", 90), new Student("Lika", 51));
        List<Student> result = new StudentUtil().levelOf(students, 52);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is("Den"));
        assertThat(result.get(1).getScope(), is(77));
    }

    @Test
    public void whenGetGroupStudentsWithNullElThenGetListStudentsBasedOnScore() {
        List<Student> students = Arrays.asList(
                new Student("Kirill", 45), null, new Student("Den", 90),
                null, new Student("Lika", 51));
        List<Student> result = new StudentUtil().levelOf(students, 47);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is("Den"));
        assertThat(result.get(1).getScope(), is(51));
    }

    @Test
    public void whenGetToStringAndCompareForStudent() {
        Student first = new Student("Kirill", 45);
        Student second = new Student("Den", 90);
        String expected = "Student{name=Kirill, scope=45}";
        int exp = first.compareTo(second);
        assertThat(first.toString(), is(expected));
        assertThat(exp, is(-1));
    }
}
