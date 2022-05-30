package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private int dateAndTime;
    private int Duration;

    public Booking() {
    }

    public Booking(int dateAndTime, int duration, Car car, WashingAssistant washingAssistant) {
        this.dateAndTime = dateAndTime;
        Duration = duration;
    }

    @ManyToOne
    private Car car;

    @ManyToMany
    @JoinTable(
            name = "BOOKING_WASHINGASSISTANT",
            joinColumns = @JoinColumn(name = "BOOKING_ID",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "WASHINGASSISTANT_ID",referencedColumnName = "ID"))
    private List<WashingAssistant> washingAssistants = new ArrayList<>();



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(int dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        if (!car.getBookings().contains(this)){
            car.getBookings().add(this);
        }
    }

    public List<WashingAssistant> getWashingAssistants() {
        return washingAssistants;
    }

    public void setWashingAssistants(List<WashingAssistant> washingAssistants) {
        this.washingAssistants = washingAssistants;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", Duration=" + Duration +
                '}';
    }
}
