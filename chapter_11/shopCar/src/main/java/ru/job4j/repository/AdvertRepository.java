package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.job4j.model.Advert;
import ru.job4j.model.State;
import ru.job4j.model.usersmodels.User;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface AdvertRepository extends PagingAndSortingRepository<Advert, Long> {

    List<Advert> findAdvertsByUser(User user);

    @Modifying(clearAutomatically = true)
    @Query("update Advert as a set a.state = ?1 where a.id = ?2")
    void update(State state, Long id);

}
