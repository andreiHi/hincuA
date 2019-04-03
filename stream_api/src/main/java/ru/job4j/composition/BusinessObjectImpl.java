package ru.job4j.composition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class BusinessObjectImpl implements BusinessObject {
    private static final Logger LOG = LogManager.getLogger(BusinessObjectImpl.class);

    private String name;
    private int age;

    public BusinessObjectImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public BusinessObject createModel(String object) {
        return new BusinessObjectImpl(object, age);
    }

    @Override
    public BusinessObject transform(BusinessObject object) {
        object.setAge(20);
        return object;
    }

    @Override
    public List<String> validate(BusinessObject object) {
        List<String> res;
        if (object.getAge() > 18) {
            res = List.of("its ok");
        } else {
            res = List.of("very bed");
        }
        return res;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int getAge() {
        return age;
    }
    @Override
    public void setAge(int age) {
        this.age = age;
    }
}
