package ru.job4j.model;

import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Image;
import ru.job4j.model.car.Model;
import ru.job4j.model.car.parts.Engine;
import ru.job4j.model.car.parts.EngineType;
import ru.job4j.model.usersmodels.User;
import ru.job4j.service.AdvertService;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 15.05.18;
 * @version $Id$
 * @since 0.1
 */
public class AdvertForm {

    private String brand;
    private String model;
    private String mileage;
    private String price;
    private String year;
    private String carcass;
    private String transmission;
    private String gearBox;
    private String engineType;
    private String volume;
    private String power;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private MultipartFile[] photo;

    public String getBrand() {
        return brand;
    }
    public String createNewAdvert(User user) {
        String create = "login";
        if (user != null) {
            Brand brand = new Brand(Long.parseLong(this.brand));
            Model model = new Model(Long.parseLong(this.model));
            Engine engine = new Engine(Integer.valueOf(this.volume), Integer.valueOf(this.power), EngineType.valueOf(this.engineType));
            List<Image> images = new ArrayList<>();
            Advert advert = new Advert(this.description, user, Integer.valueOf(this.price));
            Car car = new Car(engine, brand, model, advert, images);
            advert.setCar(car);
            new AdvertService().save(advert);
            create = "ok";
        }
        return create;
    }
    @Override
    public String toString() {
        return "AdvertForm{"
                + "brand='"
                + brand
                + '\''
                + ", model='"
                + model
                + '\''
                + ", mileage='"
                + mileage
                + '\''
                + ", price='"
                + price
                + '\''
                + ", year='"
                + year
                + '\''
                + ", carcass='"
                + carcass
                + '\''
                + ", transmission='"
                + transmission
                + '\''
                + ", gearBox='"
                + gearBox
                + '\''
                + ", engineType='"
                + engineType
                + '\''
                + ", volume='"
                + volume
                + '\''
                + ", power='"
                + power
                + '\''
                + ", photo="
                + photo.length
                + '}';
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCarcass() {
        return carcass;
    }

    public void setCarcass(String carcass) {
        this.carcass = carcass;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public MultipartFile[] getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile[] photo) {
        this.photo = photo;
    }



}
