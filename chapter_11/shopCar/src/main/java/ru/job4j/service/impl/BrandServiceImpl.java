package ru.job4j.service.impl;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.car.Brand;
import ru.job4j.repository.BrandRepository;
import ru.job4j.service.BrandService;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Service
public class BrandServiceImpl implements BrandService {
    private static final Logger LOG = LogManager.getLogger(BrandServiceImpl.class);

    @Autowired
    private BrandRepository repository;

    @Override
    public List<Brand> findAll() {
        return (List<Brand>) this.repository.findAll();
    }

    public String getAllBrandsToJson() {
        return new Gson().toJson(findAll());
    }

    @Override
    public long count() {
        return repository.count();
    }
}
