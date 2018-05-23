package ru.job4j.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.dao.Dao;
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

}
