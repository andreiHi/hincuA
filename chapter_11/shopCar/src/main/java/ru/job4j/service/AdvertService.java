package ru.job4j.service;

import ru.job4j.dao.modeldao.AdvertImpl;
import ru.job4j.model.Advert;

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
}
