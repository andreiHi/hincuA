package ru.job4j.service;

import ru.job4j.dao.modeldao.AdvertImpl;
import ru.job4j.model.Advert;
import ru.job4j.model.State;
import ru.job4j.model.User;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertService {
    private AdvertImpl advertDao;

    public AdvertService() {
        this.advertDao = new AdvertImpl();
    }

    public Long save(Advert advert) {
        advertDao.openCurrentSessionWithTransaction();
        Long id = advertDao.save(advert);
        advertDao.closeSessionWithTransaction();
        return id;
    }

    public List<Advert> getAll() {
        advertDao.openCurrentSession();
        List<Advert> adverts = advertDao.getAll();
        advertDao.closeCurrentSession();
        return adverts;
    }

    public List<Advert> getAdvertsByUser(User user) {
        advertDao.openCurrentSession();
        List<Advert>  adverts = advertDao.getAdverts(user.getId());
        advertDao.closeCurrentSession();
        return adverts;
    }

    public boolean changeState(String id, String state) {
        boolean change = false;
        advertDao.openCurrentSessionWithTransaction();
        Advert advert = advertDao.getEntityById(Long.valueOf(id));
        if (!state.equals(advert.getState().name())) {
            advert.setState(State.valueOf(state));
            change = true;
        }
        advertDao.closeSessionWithTransaction();
        return change;
    }

    public List<Advert> getByQuery(String s) {
        advertDao.openCurrentSession();
        List<Advert> adverts = advertDao.getByQuery(s);
        advertDao.closeCurrentSession();
        return adverts;
    }
    public List<Advert> getByQueryWithJoin(String s) {
        advertDao.openCurrentSession();
        List<Advert> adverts = advertDao.getByQueryWithJoin(s);
        advertDao.closeCurrentSession();
        return adverts;
    }

}
