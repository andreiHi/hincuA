package ru.job4j.service;

import ru.job4j.model.usersmodels.LoginForm;
import ru.job4j.model.usersmodels.RegistrationForm;
import ru.job4j.model.usersmodels.User;

import java.util.List;

/**
 * .
 * @author Hincu Andrei (andreih1981@gmail.com) by 23.05.18;
 * @version $Id$
 * @since 0.1
 */
public interface UserService {

    List<User> getAll();

    String saveIfValid(RegistrationForm form);

    boolean login(LoginForm loginForm);
}
