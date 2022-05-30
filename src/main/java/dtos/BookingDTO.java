package dtos;

import entities.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingDTO {
    private long id;
    private int dateAndTime;
    private int duration;

    public BookingDTO(long id, int dateAndTime, int duration) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public BookingDTO(Booking b) {
        this.id = b.getId();
        this.dateAndTime = b.getDateAndTime();
        this.duration = b.getDuration();
    }

    public static List<BookingDTO> getDtos(List<Booking> bs){
        List<BookingDTO> bdtos = new ArrayList<>();
        bs.forEach(b->bdtos.add(new BookingDTO(b)));
        return bdtos;
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
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", duration=" + duration +
                '}';
    }
}
