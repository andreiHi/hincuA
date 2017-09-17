package ru.job4j.litle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
/**
 * Test.
 * @author Hincu Andrei (andreih1981@gmail.com) by 17.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ConvertListTest {
    /**
     *Тест конвертации двумерного массива в ArrayList.
     */
    @Test
    public void whenConvertArrayToArrayList() {
        ConvertList convertList = new ConvertList();
        int[][]array = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        ArrayList<Integer> list = (ArrayList) convertList.toList(array);
        ArrayList<Integer> ex = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
            ex.add(array[i][j]);
            }
        }
        assertThat(list, is(ex));
    }

    /**
     * Test сонвертации ArrayList в двумерный массив с заполнением пустых ячеек нулями.
     */
    @Test
    public void whenConvertArrayListToArray() {
        ConvertList convertList = new ConvertList();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(i);
        }
        int[][]array = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 0, 0}
        };
        int[][]result = convertList.toArray(list, 3);
        assertThat(result, is(array));
    }
}










