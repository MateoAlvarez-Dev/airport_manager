package model;

import database.Database;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerModel extends GeneralModel {

    private Database database;

    public PassengerModel(){
        super();
        this.database = Database.getInstance();
    }

    public Object create(Object object) {
        ResultSet result = super.create("passengers", object);
        Passenger passenger = (Passenger) object;

        try{
            while(result.next()){
                passenger.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while creating the passenger... " + e.getMessage());
        }

        this.database.disconnect();
        return passenger;
    }

    public ArrayList findAll() {
        ResultSet result = super.findAll("passengers");
        ArrayList passengers = new ArrayList<>();

        try{
            while(result.next()){
                Passenger passenger = new Passenger();
                passenger.setId(result.getInt("id"));
                passenger.setName(result.getString("name"));
                passenger.setLast_name(result.getString("last_name"));
                passenger.setIdentity(result.getString("identity"));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.database.disconnect();
        return passengers;
    }

    public Object findById(int id){
        ResultSet result = super.findById("passengers", id);
        Passenger passenger = null;
        try{
            while (result.next()){
                passenger = new Passenger();
                passenger.setId(result.getInt("id"));
                passenger.setName(result.getString("name"));
                passenger.setLast_name(result.getString("last_name"));
                passenger.setIdentity(result.getString("identity"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while loading the passenger...");
        }

        this.database.disconnect();
        return passenger;
    }

    public ArrayList<Passenger> findByName(String passengerName) {
        Connection connection = this.database.connect();
        ArrayList<Passenger> passengerList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM passengers WHERE name LIKE ? OR last_name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + passengerName + "%");
            preparedStatement.setString(2, "%" + passengerName + "%");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                Passenger passenger = new Passenger();
                passenger.setId(result.getInt("id"));
                passenger.setName(result.getString("name"));
                passenger.setLast_name(result.getString("last_name"));
                passenger.setIdentity(result.getString("identity"));
                passengerList.add(passenger);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while listing the data...");
        }

        return passengerList;
    }

    public boolean update(Object object){
        return super.update("passengers", object);
    }

    public boolean delete(int id){
        return super.delete("passengers", id);
    }
}