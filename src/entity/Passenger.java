package entity;

public class Passenger implements EntityInterface{
    private int id;
    private String username;
    private String last_name;
    private String identity_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    @Override
    public String[] ExportSetMethodNames() {
        return new String[0];
    }

    @Override
    public String[] ExportGetMethodNames() {
        return new String[]{ "getId", "getUsername", "getLast_name", "getIdentity_number"};
    }
}
