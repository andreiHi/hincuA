package ru.job4j.model;

import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity
@Table(name = "Users")
public class User extends Persistent {

    private static final long serialVersionUID = -5971624644618934547L;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    @Column(name = "phone", unique = true)
    private String phone;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Advert> adverts = new ArrayList<>();

    public User() {
        super();
    }
    public User(Long id) {
        super(id);
    }
    public User(String login, String email, String password, String phone) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    @Transient
    public static final User UNKNOWN_USER = new User();

    @Override
    public String toString() {
        return "User{"
                + "id="
                + getId()
                + ", login='"
                + login
                + '\''
                + ", email='"
                + email
                + '\''
                + ", password='"
                + password
                + '\''
                + ", phone="
                + phone
                + '}';
    }

    public String getLogin() {
        return login;
    }
    public  String findEquals(User user) {
        StringBuilder sb = new StringBuilder();
        if (login.equals(user.login)) {
            sb.append("Login ");
        }
        if (email.equals(user.email)) {
            sb.append("Email ");
        }
        if (phone.equals(user.phone)) {
            sb.append("Phone ");
        }
        sb.append("is used.");
        return sb.toString();
    }
    public boolean checkPassword(String password) {
        return this.password != null && this.password.equals(password);
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", login);
        jsonObject.put("email", email);
        jsonObject.put("phone", phone);
        return jsonObject;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public List<Advert> getAdverts() {
//        return adverts;
//    }
//
//    public void setAdverts(List<Advert> adverts) {
//        this.adverts = adverts;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return   Objects.equals(phone, user.phone)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password);
//                && Objects.equals(adverts, user.adverts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, email, password, phone/*, adverts*/);
    }
}
