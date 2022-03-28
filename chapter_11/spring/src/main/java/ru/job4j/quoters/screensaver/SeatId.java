package ru.job4j.quoters.screensaver;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatId implements Serializable {
    private static final long serialVersionUID = 7343882359051180720L;
    @Column(name = "aircraft_code", nullable = false, length = 3)
    private String aircraftCode;
    @Column(name = "seat_no", nullable = false, length = 4)
    private String seatNo;

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode, seatNo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeatId entity = (SeatId) o;
        return Objects.equals(this.aircraftCode, entity.aircraftCode) &&
                Objects.equals(this.seatNo, entity.seatNo);
    }
}
