package ru.job4j.composition;

import java.util.List;
import java.util.function.Function;

/**
 * Композиция функций
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public interface BusinessObject {

    BusinessObject createModel(String object);

    BusinessObject transform(BusinessObject object);

    List<String> validate(BusinessObject object);

    String getName();
    void setName(String name);
    int getAge();
    void setAge(int age);


}
