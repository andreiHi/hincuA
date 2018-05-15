package ru.job4j.model.car;

import org.json.simple.JSONObject;
import ru.job4j.model.Persistent;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity @Table(name = "model")
public class Model extends Persistent {

    private static final long serialVersionUID = -8282692270423672443L;
    private String name;

    @ManyToOne()
    @JoinColumn(name = "id_brand", referencedColumnName = "id")
    private Brand brand;

    public Model() {
        super();
    }
    public Model(String name, Brand brand) {
        super();
        this.name = name;
        this.brand = brand;
    }
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("name", name);
        return jsonObject;
    }
    public Model(Long id) {
        super(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return String.format("Model={id = %d, name = %s}", getId(), name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return Objects.equals(name, model.name)
                && Objects.equals(brand, model.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand);
    }
}
