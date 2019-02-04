package ru.job4j.univer;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.03.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class IndexValue<T> {

    private  int index;
    private  T value;

    public IndexValue(int index, T value) {
        this.index = index;
        this.value = value;
    }
    public static <T> Stream<IndexValue<T>> withIndeces(List<T> list) {
        return IntStream.range(0, list.size())
                .mapToObj(idx -> new IndexValue<>(idx, list.get(idx)));
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
