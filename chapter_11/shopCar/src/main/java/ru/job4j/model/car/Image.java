package ru.job4j.model.car;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.Persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity
@Table(name = "image")
public class Image extends Persistent {
    private static final Logger LOG = LogManager.getLogger(Image.class);
    @ManyToOne @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;
//    private byte[] img;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image(Car car) {
        super();
        this.car = car;
    }
    public Image(String name, String path, Car car) {
        this.name = name;
        this.path = path;
        this.car = car;
    }
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Image() {
        super();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

//    public byte[] getImg() {
//        return img;
//    }

//    public void setImg(byte[] image) {
//        this.img = image;
//    }
public JSONObject toJson() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", getId());
    jsonObject.put("name", name);
    jsonObject.put("path", path);
    return jsonObject;
}
    @Override
    public String toString() {
        return "Image "
                + "id=" + getId()
                + ", name ='"
                + name
                + " car_id ="
                + car.getId()
                + " path ="
                + path
                + '\'';
    }
}
