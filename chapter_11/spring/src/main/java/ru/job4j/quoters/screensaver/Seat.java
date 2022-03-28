package ru.job4j.quoters.screensaver;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat {
    @EmbeddedId
    private SeatId id;

    @Column(name = "fare_conditions", nullable = false, length = 10)
    private String fareConditions;

    public String getFareConditions() {
        return fareConditions;
    }

    public void setFareConditions(String fareConditions) {
        this.fareConditions = fareConditions;
    }

    public SeatId getId() {
        return id;
    }

    public void setId(SeatId id) {
        this.id = id;
    }
}
