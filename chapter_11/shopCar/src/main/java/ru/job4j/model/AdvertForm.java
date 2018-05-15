package ru.job4j.model;

import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Image;
import ru.job4j.model.car.Model;
import ru.job4j.model.car.parts.*;
import ru.job4j.model.usersmodels.User;

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
    private int mileage;
    private int price;
    private int year;
    private Carcass carcass;
    private Transmission transmission;
    private Gearbox gearBox;
    private EngineType engineType;
    private String volume;
    private int power;
    private String description;
    private MultipartFile[] photo;


    public String getBrand() {
        return brand;
    }
    public String createNewAdvert(User user) {
        String create = "login";
        if (user != null) {
            Engine engine = completeEngine();
            List<Image> images = new ArrayList<>();
            Advert advert = new Advert(this.description, user, this.price);
            Car car = new Car(advert, images, this.mileage, this.carcass, this.gearBox, this.transmission, this.year);
            car.setEngine(engine);
            car.setBrand(new Brand(Long.valueOf(this.brand)));
            car.setModel(new Model(Long.valueOf(this.model)));
            advert.setCar(car);
            System.out.println(advert);
        //    new AdvertService().save(advert);
            create = "ok";
        }
        return create;
    }
    private Engine completeEngine() {
        int v = this.engineType.name().equals("Electro") ? 0 : Integer.parseInt(this.volume);
        return new Engine(v, this.power, this.engineType);
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Carcass getCarcass() {
        return carcass;
    }

    public void setCarcass(String carcass) {
        this.carcass = Carcass.valueOf(carcass);
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = Transmission.valueOf(transmission);
    }

    public Gearbox getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = Gearbox.valueOf(gearBox);
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = EngineType.valueOf(engineType);
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public MultipartFile[] getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile[] photo) {
        this.photo = photo;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
