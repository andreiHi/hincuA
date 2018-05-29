package ru.job4j.service;

import ru.job4j.model.Advert;
import ru.job4j.model.usersmodels.User;

import java.util.List;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface AdvertService {

    List<Advert> getAdverts(User user, Map req);

    Advert save(Advert advert);

    void changeState(Long id, String state);
}
