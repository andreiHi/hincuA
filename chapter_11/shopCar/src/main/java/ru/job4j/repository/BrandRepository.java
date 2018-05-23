package ru.job4j.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.job4j.model.car.Brand;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {

}
