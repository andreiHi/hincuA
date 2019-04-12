package ru.job4j.optional;

import java.util.Optional;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Example {

    class UsingFlatMap {
        /**
         * flatMap используется для разворота Optional
         * map оборачивает в Optional
         * @param person
         * @return
         */
        String getNameInsurance(Person person) {
            return Optional.ofNullable(person)
                    .flatMap(Person::getCar)
                    .flatMap(Car::getInsurance)
                    .map(Insurance::getName)
                    .orElse("Unknown");
        }
    }

    class Person {
        Optional<Car> getCar() {
            return Optional.empty();
        }
    }
    class Car {
        Optional<Insurance> getInsurance() {
            return Optional.empty();
        }
    }

    class Insurance {

        String getName() {
            return "";
        }

    }

}

