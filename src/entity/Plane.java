package entity;

public class Plane implements EntityInterface{
    private int id;
    private String model;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String[] ExportSetMethodNames() {
        return new String[0];
    }

    @Override
    public String[] ExportGetMethodNames() {
        return new String[]{ "getId", "getModel", "getCapacity" };
    }
}
