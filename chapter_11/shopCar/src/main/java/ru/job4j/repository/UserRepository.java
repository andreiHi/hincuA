package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.model.usersmodels.User;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.05.2018.
 * @version $Id$.
 * @since 0.1.
 */

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query(value = "select u FROM User as u where u.login = :login or u.email = :email or u.phone = :phone")
    List<User> checkIfPresent(@Param("login") String login, @Param("email") String email, @Param("phone") String phone);

    User findByLogin(String login);


}
