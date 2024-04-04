package entity;

public class Passenger implements EntityInterface{
    private int id;
    private String name;
    private String last_name;
    private String identity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String[] ExportSetMethodNames() {
        return new String[0];
    }

    @Override
    public String[] ExportGetMethodNames() {
        return new String[]{ "getId", "getName", "getLast_name", "getIdentity"};
    }
}
