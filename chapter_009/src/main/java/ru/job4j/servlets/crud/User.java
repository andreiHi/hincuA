package ru.job4j.servlets.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Calendar;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class User {
    private static final Logger LOG = LogManager.getLogger(User.class);
    private  String name;
    private String ogin;
    private String email;
    private Calendar createDate;
}
