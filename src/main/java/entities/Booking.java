package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int dateAndTime;
    private int Duration;

    public Booking() {
    }

    public Booking(int dateAndTime, int duration) {
        this.dateAndTime = dateAndTime;
        Duration = duration;
    }

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

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", Duration=" + Duration +
                '}';
    }
}
