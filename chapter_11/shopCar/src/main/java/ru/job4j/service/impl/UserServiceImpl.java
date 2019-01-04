package ru.job4j.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.usersmodels.LoginForm;
import ru.job4j.model.usersmodels.RegistrationForm;
import ru.job4j.model.usersmodels.User;
import ru.job4j.repository.UserRepository;
import ru.job4j.service.UserService;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Service
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public String saveIfValid(RegistrationForm form) {
        String result;
        List<User> users = userRepository.checkIfPresent(form.getLogin(), form.getEmail(), form.getPhone());
        if (users.isEmpty()) {
            userRepository.save(form.createUser());
            result = "ok";
            LOG.info("User was saved.");
        } else {
            result = users.get(0).findEquals(form);
            LOG.info(result);
        }
        return result;
    }

    @Override
    public boolean login(LoginForm loginForm) {
        boolean exist = false;
        User user = userRepository.findByLogin(loginForm.getLogin());
        if (user != null) {
            if (loginForm.checkPass(user)) {
                exist = true;
                LOG.info("User was login successful.");
            }
        }
        return exist;
    }
}
