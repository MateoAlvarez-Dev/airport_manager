package model;

import database.Database;
import entity.Plane;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaneModel extends GeneralModel {

    private Database database;

    public PlaneModel(){
        super();
        this.database = Database.getInstance();
    }

    public Object create(Object object) {
        ResultSet result = super.create("airplanes", object);
        Plane plane = (Plane) object;

        try{
            while(result.next()){
                plane.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while creating the plane... " + e.getMessage());
        }

        this.database.disconnect();
        return plane;
    }

    public ArrayList<Plane> findAll() {
        ResultSet result = super.findAll("airplanes");
        ArrayList<Plane> planes = new ArrayList<>();

        try{
            while(result.next()){
                Plane plane = new Plane();
                plane.setId(result.getInt("id"));
                plane.setModel(result.getString("model"));
                plane.setCapacity(result.getInt("capacity"));
                planes.add(plane);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.database.disconnect();
        return planes;
    }

    public Object findById(int id){
        ResultSet result = super.findById("airplanes", id);
        Plane plane = null;
        try{
            while (result.next()){
                plane = new Plane();
                plane.setId(result.getInt("id"));
                plane.setModel(result.getString("model"));
                plane.setCapacity(result.getInt("capacity"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while loading the plane...");
        }

        this.database.disconnect();
        return plane;
    }

    public boolean update(Object object){
        return super.update("airplanes", object);
    }

    public boolean delete(int id){
        return super.delete("airplanes", id);
    }
}
