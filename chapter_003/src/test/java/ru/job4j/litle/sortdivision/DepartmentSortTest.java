package ru.job4j.litle.sortdivision;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.03.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class DepartmentSortTest {
    @Test
    public void sortDepsAsc() {
        DepartmentSort departmentSort = new DepartmentSort();
        departmentSort.addDepartment("K1\\SK1");
        departmentSort.addDepartment("K1\\SK2");
        departmentSort.addDepartment("K1\\SK1\\SSK1");
        departmentSort.addDepartment("K1\\SK1\\SSK2");
        departmentSort.addDepartment("K2");
        departmentSort.addDepartment("K2\\SK1\\SSK1");
        departmentSort.addDepartment("K2\\SK1\\SSK2");
        ArrayList<String> result = new ArrayList<>(departmentSort.sortDepsAsc());
        ArrayList<String> expect = new ArrayList<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK2");
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK2");
        assertThat(result, is(expect));
    }

    @Test
    public void sortDepsDesc() {
        DepartmentSort departmentSort = new DepartmentSort();
        departmentSort.addDepartment("K1\\SK1");
        departmentSort.addDepartment("K1\\SK2");
        departmentSort.addDepartment("K1\\SK1\\SSK1");
        departmentSort.addDepartment("K1\\SK1\\SSK2");
        departmentSort.addDepartment("K2");
        departmentSort.addDepartment("K2\\SK1\\SSK1");
        departmentSort.addDepartment("K2\\SK1\\SSK2");
        ArrayList<String> result = new ArrayList<>(departmentSort.sortDepsDesc());
        ArrayList<String> expect = new ArrayList<>();
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K1");
        expect.add("K1\\SK2");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK1");
        assertThat(result, is(expect));
    }
}
