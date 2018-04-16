package ru.job4j.dao.modeldao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.dao.AbstractController;
import ru.job4j.model.car.Model;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ModelImpl extends AbstractController<Model, Long> {
    private static final Logger LOG = LogManager.getLogger(ModelImpl.class);

    @Override @SuppressWarnings("unchecked")
    public List<Model> getAll() {
        return getCurrentSession().createQuery("from Model ").list();
    }

    @Override
    public Model getEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void delete(Model entity) {

    }

    @Override
    public Long save(Model entity) {
        return null;
    }

    @Override
    public void update(Model entity) {

    }
    public List<Model> getModelsByBrandId(long id) {
      Query query = getCurrentSession().createQuery("from Model  where brand.id =:id ");
      query.setParameter("id", id);
      return (List<Model>) query.getResultList();
    }
}
