package ru.job4j.model;

import org.json.simple.JSONObject;
import ru.job4j.model.car.Car;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 18.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity
@Table(name = "advert")
public class Advert extends Persistent {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd MM YYYY");
    private static final long serialVersionUID = 8280964074040472053L;
    private Timestamp data;
    @Column(name = "description", length = 1000)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Advert advert = (Advert) o;

        if (price != advert.price) {
            return false;
        }
        if (data != null ? !data.equals(advert.data) : advert.data != null) {
            return false;
        }
        if (description != null ? !description.equals(advert.description) : advert.description != null) {
            return false;
        }
        if (state != advert.state) {
            return false;
        }
        if (user != null ? !user.equals(advert.user) : advert.user != null) {
            return false;
        }
        return car != null ? car.equals(advert.car) : advert.car == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }

    private int price;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private Car car;

    public Advert(String description, User user, Car car, int price) {
        this.data = new Timestamp(System.currentTimeMillis());
        this.description = description;
        this.user = user;
        this.car = car;
        this.state = State.NEW;
        this.price = price;
    }

    public Advert() {
        this.data = new Timestamp(System.currentTimeMillis());
        this.state = State.NEW;

    }
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("user", user.toJson());
        json.put("car", car.toJson());
        json.put("description", description);
        json.put("data", FORMAT.format(data));
        json.put("state", state.name());
        json.put("price", price);
        return json;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Advert() {
//        super();
//    }
    private Advert(Long id) {
        super(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Advert{"
                + "id="
                + getId()
                + ", data="
                + data
                + ", description='"
                + description
                + '\''
                + " user = "
                + user
                + ", state="
                + state
                + ", car="
                + car
                + ", price="
                + price
                + '}';
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
