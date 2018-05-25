package ru.job4j.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Model;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 24.05.18;
 * @version $Id$
 * @since 0.1
 */
public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {
    List<Model> findByBrand(Brand brand);
}
