package ru.job4j.model.car;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private byte[]image;

    public Image(Car car) {
        super();
        this.car = car;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
