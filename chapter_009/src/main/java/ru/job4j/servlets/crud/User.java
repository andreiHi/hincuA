package ru.job4j.servlets.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class User {
    private static final Logger LOG = LogManager.getLogger(User.class);
    private  String name;
    private String login;
    private String email;
    private Calendar createDate;
    private String date;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public User(String login, String name, String email) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.createDate = Calendar.getInstance();
        this.date = new SimpleDateFormat("dd-MM-YYYY").format(createDate.getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public String getDate() {
        return date;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
        this.date = new SimpleDateFormat("dd-MM-YYYY").format(createDate.getTime());
    }

    @Override
    public String toString() {
        return "User "
                + "Name = '"
                + name
                + '\''
                + ", Login = '"
                + login
                + '\''
                + ", Email = '"
                + email
                + '\''
                + ", Date = "
                + date;
        //+ createDate.getTime().toString()

    }
}
