package ru.job4j.composition;

import java.util.List;
import java.util.function.Function;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Process {

    private BusinessObject object;

    public Process(BusinessObject object) {
        this.object = object;
    }


    public List<String> process(String param) {
        BusinessObject model = object.createModel(param);
        BusinessObject transform = object.transform(model);
        return object.validate(transform);
    }

    public List<String> process3(String param) {
        return object.validate(object.transform(object.createModel(param)));
    }


    public List<String> prosess4(String param) {
        return first(object::createModel)
                .andThen(object::transform)
                .andThen(object::validate)
                .apply(param);
    }

    public static <K, V> Function<K, V> first(Function<K, V> f) {
        return f;
    }
}
