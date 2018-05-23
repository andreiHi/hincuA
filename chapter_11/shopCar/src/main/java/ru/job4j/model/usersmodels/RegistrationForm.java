package ru.job4j.model.usersmodels;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class RegistrationForm extends LoginForm {
    private String email;
    private String phone;

    public RegistrationForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("RegistrationForm{login='%s', password='%s', email='%s', phone='%s'}'", getLogin(), getPassword(), this.email, this.phone);
    }

    public User createUser() {
        return new User(getLogin(), email, getPassword(), phone);
    }
}
