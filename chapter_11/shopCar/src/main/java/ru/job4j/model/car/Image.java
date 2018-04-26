package ru.job4j.model.car;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.Persistent;
import ru.job4j.model.User;

import javax.persistence.*;

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
    public Image(String name, String largeImage, String smallImage, Car car) {
        this.name = name;
        this.largeImage = largeImage;
        this.car = car;
        this.smallImage = smallImage;
    }
    public Image(String name, String largeImage, String smallImage) {
        this.name = name;
        this.largeImage = largeImage;
        this.smallImage = smallImage;
    }
    private String largeImage;
    private String smallImage;

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String path) {
        this.largeImage = path;
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
    jsonObject.put("largeImage", largeImage);
    jsonObject.put("smallImage", smallImage);
    return jsonObject;
}

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @Override
    public String toString() {
        return "Image "
                + "id=" + getId()
                + ", name ='"
                + name
                + " car_id ="
                + car.getId()
                + " large ="
                + largeImage
                + " small ="
                + smallImage
                + '\'';
    }
}
