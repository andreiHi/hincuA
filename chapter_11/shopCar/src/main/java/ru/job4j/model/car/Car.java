package ru.job4j.model.car;

import org.json.simple.JSONObject;
import ru.job4j.model.Advert;
import ru.job4j.model.Persistent;
import ru.job4j.model.car.parts.Carcass;
import ru.job4j.model.car.parts.Engine;
import ru.job4j.model.car.parts.Gearbox;
import ru.job4j.model.car.parts.Transmission;

import javax.persistence.*;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 18.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity
@Table(name = "cars")
public class Car extends Persistent {

    private static final long serialVersionUID = 785710110674611001L;

    /**
     * Пройденный путь
     */
    private int mileage;
    /**
     * Год выпуска
     */
    private int year;

    public Car() {
        super();
    }
    public Car(Long id) {
        super(id);
    }
    public Car(Engine engine, Brand brand, Model model, Advert advert) {
        this.engine = engine;
        this.brand = brand;
        this.model = model;
        this.advert = advert;
    }
        public Car(Engine engine, Brand brand, Model model, Advert advert, List<Image> images) {
        this.engine = engine;
        this.brand = brand;
        this.model = model;
        this.advert = advert;
        this.images = images;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_engine")
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mobel")
    private Model model;

    @Enumerated(EnumType.STRING)
    private Carcass carcass;

    @Enumerated(EnumType.STRING)
    private Gearbox gearBox;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @OneToOne()
    @JoinColumn(name = "id_advert")
    private Advert advert;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mileage", mileage);
        json.put("year", year);
        json.put("engine", engine.toJson());
        json.put("brand", brand.toJson());
        json.put("model", model.toJson());
        json.put("carcass", carcass.name());
        json.put("gearBox", gearBox.name());
        json.put("transmission", transmission.name());
        json.put("images", imagesToJson());
        return json;
    }
    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }


    private JSONObject imagesToJson() {
        JSONObject json = new JSONObject();
        images.forEach(image -> json.put("image", image.toJson()));
        return json;
    }
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Gearbox getGearBox() {
        return gearBox;
    }

    public void setGearBox(Gearbox box) {
        this.gearBox = box;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int milage) {
        this.mileage = milage;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Carcass getCarcass() {
        return carcass;
    }

    public void setCarcass(Carcass carcass) {
        this.carcass = carcass;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id="
                + getId()
                + ", mileage="
                + mileage
                + ", year="
                + year
                + ", engine="
                + engine
                + ", brand="
                + brand
                + ", model="
                + model
                + ", carcass="
                + carcass
                + ", gearBox="
                + gearBox
                + ", transmission="
                + transmission
                + ", image= "
                + images
                + '}';
    }
}

