package entity;

public class Booking implements EntityInterface{

    private int id;
    private String booking_date;
    private int seat;
    private int id_passenger;
    private int id_flight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(int id_passenger) {
        this.id_passenger = id_passenger;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    @Override
    public String[] ExportSetMethodNames() {
        return new String[0];
    }

    @Override
    public String[] ExportGetMethodNames() {
        return new String[]{ "getId", "getBooking_date", "getSeat", "getId_passenger", "getId_flight" };
    }
}
