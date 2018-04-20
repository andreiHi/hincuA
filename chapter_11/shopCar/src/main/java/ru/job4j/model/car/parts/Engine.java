package ru.job4j.model.car.parts;

import org.json.simple.JSONObject;
import ru.job4j.model.Persistent;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.03.2018.
 * @version $Id$.
 * @since 0.1.
 */

@Entity
@Table(name = "engine")
public class Engine extends Persistent {
    private static final long serialVersionUID = 3182545441241279870L;

    private int volume;
    @Enumerated(EnumType.STRING)
    private EngineType fuelType;
    private int power;

    public Engine() {
        super();
    }
    public Engine(Long id) {
        super(id);
    }
    public Engine(int volume, int power, EngineType fuelType) {
        super();
        this.volume = volume;
        this.fuelType = fuelType;
        this.power = power;
    }
//    @OneToMany(mappedBy = "engine", fetch = FetchType.LAZY)
//    private List<Car> car = new ArrayList<>();

    @Override
    public String toString() {
        return "Engine{"
                + "id="
                + getId()
                + ", volume="
                + volume
                + ", fuelType="
                + fuelType
                + ", power="
                + power
                + '}';
    }
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("volume", volume);
        jsonObject.put("fuelType", fuelType.name());
        jsonObject.put("power", power);
        return jsonObject;
    }
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public EngineType getFuelType() {
        return fuelType;
    }

    public void setFuelType(EngineType fuelType) {
        this.fuelType = fuelType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

//    public List<Car> getCar() {
//        return car;
//    }
//
//    public void setCar(List<Car> car) {
//        this.car = car;
//    }
}
