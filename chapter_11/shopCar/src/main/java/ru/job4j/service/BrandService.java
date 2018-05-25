package ru.job4j.service;

import ru.job4j.model.car.Brand;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface BrandService {

    List<Brand> findAll();

    String getAllBrandsToJson();

    long count();
}
