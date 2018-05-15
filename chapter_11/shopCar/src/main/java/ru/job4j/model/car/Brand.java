package ru.job4j.model.car;

import org.json.simple.JSONObject;
import ru.job4j.model.Persistent;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity
@Table(name = "brand")
public class Brand extends Persistent {

    private static final long serialVersionUID = 7814551052352183461L;

    private String name;

//    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Model> models = new ArrayList<>();

    public Brand() {
        super();
    }

    public Brand(Long id) {
        super(id);
    }
    public Brand(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("name", name);
        return jsonObject;
    }
//    public List<Model> getModels() {
//        return models;
//    }
//
//    public void setModels(List<Model> models) {
//        this.models = models;
//    }

    @Override
    public String toString() {
        return String.format("Brand={id = %d, name = %s}", getId(), name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
