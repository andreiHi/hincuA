package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class CarSold {
    private static final Logger LOG = LogManager.getLogger(CarSold.class);
    private long id;
    private String state;

    public boolean changeState() {
     //   boolean result = new AdvertService().changeState(id, state);
        return false;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CarSold() {

    }
}
