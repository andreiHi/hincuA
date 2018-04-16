package ru.job4j.dao.modeldao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.dao.AbstractController;
import ru.job4j.model.car.Brand;

import java.util.List;

/**
 * Не используемые методы могут быть использованы в адменской части
 * которая не входит в рамки этого задания
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class BrandImpl extends AbstractController<Brand, Long> {
    private static final Logger LOG = LogManager.getLogger(BrandImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<Brand> getAll() {
        return getCurrentSession().createQuery("from Brand ").list();
    }

    @Override
    public Brand getEntityById(Long id) {
        return getCurrentSession().get(Brand.class, id);
    }

    @Override
    public boolean delete(Long id) {
        boolean flag = false;
        Brand brand = getEntityById(id);
        if (brand != null) {
            flag = true;
            getCurrentSession().delete(brand);
        }
        return flag;
    }

    @Override
    public void delete(Brand entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public Long save(Brand entity) {
        return (Long) getCurrentSession().save(entity);
    }

    @Override
    public void update(Brand entity) {
        getCurrentSession().update(entity);
    }
}
