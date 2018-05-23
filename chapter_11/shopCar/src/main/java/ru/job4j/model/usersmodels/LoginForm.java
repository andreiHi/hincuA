package ru.job4j.model.usersmodels;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class LoginForm {
    private String login;
    private String password;
    private User user;

    public LoginForm() {
    }

    public LoginForm(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User getUser() {
        return user;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("LoginForm{login='%s', password='%s'}", login, password);
    }

    public boolean checkPass(User user) {
        boolean ex = user.getPassword().equals(this.password);
        if (ex) {
            this.user = user;
        }
        return ex;
    }
}
