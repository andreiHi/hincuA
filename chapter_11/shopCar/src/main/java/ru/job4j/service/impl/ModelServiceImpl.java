package ru.job4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.car.Model;
import ru.job4j.repository.ModelRepository;
import ru.job4j.service.ModelService;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 24.05.18;
 * @version $Id$
 * @since 0.1
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository repository;

    @Override
    public List<Model> findByBrandId(long id) {
        return repository.findByBrand_Id(id);
    }
}
