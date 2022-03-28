package ru.job4j.builder.stream;

import java.util.List;
import java.util.function.Predicate;

public interface Builder {
    Builder setSource(List source);

    Builder setPredicate(Predicate filter);

    Job4JStream build();
}
