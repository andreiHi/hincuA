package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.usersmodels.User;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.05.2018.
 * @version $Id$.
 * @since 0.1.
 */

public interface UserRepository extends CrudRepository<User, Long> {

}
