package entity;

public class Flight implements EntityInterface{

    private int id;
    private String destination;
    private String departure_date;
    private String departure_time;
    private int id_airplane;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public int getId_airplane() {
        return id_airplane;
    }

    public void setId_airplane(int id_airplane) {
        this.id_airplane = id_airplane;
    }

    @Override
    public String[] ExportSetMethodNames() {
        return new String[0];
    }

    @Override
    public String[] ExportGetMethodNames() {
        return new String[]{ "getId", "getDestination", "getDeparture_date", "getDeparture_time", "getId_airplane" };
    }
}
