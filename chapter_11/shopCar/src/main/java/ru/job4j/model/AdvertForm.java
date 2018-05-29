package ru.job4j.model;

import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Model;
import ru.job4j.model.car.parts.*;
import ru.job4j.service.ImageService;

/**
 * .
 * @author Hincu Andrei (andreih1981@gmail.com) by 15.05.18;
 * @version $Id$
 * @since 0.1
 */
public class AdvertForm {

    private Car car = new Car();
    private Engine engine = new Engine();
    private Advert advert = new Advert();
    private MultipartFile[] photo;

    public Advert createNewAdvert(String savePath) {
        car.setEngine(engine);
        advert.setCar(car);
        car.setAdvert(advert);
        ImageService service = new ImageService();
        car.setImages(service.saveImages(photo, savePath, car));
        return this.advert;
    }

    @Override
    public String toString() {
        return String.format("AdvertForm{Advert={%s}, Engine={%s}, Car={%s}}", advert, engine, car);
    }

    public void setBrand(Long brand) {
        car.setBrand(new Brand(brand));
    }

    public void setModel(Long model) {
        car.setModel(new Model(model));
    }

    public void setMileage(int mileage) {
        this.car.setMileage(mileage);
    }

    public void setPrice(int price) {
        this.advert.setPrice(price);
    }

    public void setYear(int year) {
        this.car.setYear(year);
    }

    public void setCarcass(String carcass) {
        this.car.setCarcass(Carcass.valueOf(carcass));
    }

    public void setTransmission(String transmission) {
        this.car.setTransmission(Transmission.valueOf(transmission));
    }

    public void setGearBox(String gearBox) {
        this.car.setGearBox(Gearbox.valueOf(gearBox));
    }

    public void setEngineType(String engineType) {
        this.engine.setFuelType(EngineType.valueOf(engineType));
    }

    public void setVolume(int volume) {
        this.engine.setVolume(volume);
    }

    public void setPower(int power) {
        this.engine.setPower(power);
    }

    public void setPhoto(MultipartFile[] photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.advert.setDescription(description);
    }

}
