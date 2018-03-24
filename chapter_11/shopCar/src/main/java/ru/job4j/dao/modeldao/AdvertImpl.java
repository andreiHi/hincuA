package ru.job4j.dao.modeldao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.dao.AbstractController;
import ru.job4j.model.Advert;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertImpl extends AbstractController<Advert, Long> {
    private static final Logger LOG = LogManager.getLogger(AdvertImpl.class);
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<Advert> getAll() {
//        return transaction(session -> session.createQuery("from Advert ").list());
//    }
//
//    @Override
//    public Advert getEntityById(Long id) {
//        return transaction(session -> session.get(Advert.class, id));
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        return transaction(session -> {
//            session.delete(getEntityById(id));
//            return true;
//        });
//    }

    @Override
    public List<Advert> getAll() {
        return null;
    }

    @Override
    public Advert getEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void delete(Advert entity) {

    }

    @Override
    public Long save(Advert entity) {
        return null;
    }

    @Override
    public void update(Advert entity) {

    }
//    @Override
//    public boolean delete(Advert entity) {
//        return transaction(session -> {
//            session.delete(entity);
//            return true;
//        });
//    }
//
//    @Override
//    public boolean update(Advert entity) {
//        return transaction(session -> {session.update(entity);
//        return true;
//        });
//    }
//
//    @Override
//    public Long save(Advert advert) {
//        return transaction(session -> (Long) session.save(advert));
//    }
}
