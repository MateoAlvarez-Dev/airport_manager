package model;

import database.Database;
import entity.Flight;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlightModel extends GeneralModel {

    private Database database;

    public FlightModel(){
        super();
        this.database = Database.getInstance();
    }

    public Object create(Object object) {
        ResultSet result = super.create("flights", object);
        Flight flight = (Flight) object;

        try{
            while(result.next()){
                flight.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while creating the flight... " + e.getMessage());
        }

        this.database.disconnect();
        return flight;
    }

    public ArrayList findAll() {
        ResultSet result = super.findAll("flights");
        ArrayList flights = new ArrayList<>();

        try{
            while(result.next()){
                Flight flight = new Flight();
                flight.setId(result.getInt("id"));
                flight.setDeparture_date(result.getString("departure_date"));
                flight.setDeparture_time(result.getString("departure_time"));
                flight.setDestination(result.getString("destination"));
                flight.setId_airplane(result.getInt("id_airplane"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.database.disconnect();
        return flights;
    }

    public Object findById(int id){
        ResultSet result = super.findById("flights", id);
        Flight flight = null;
        try{
            while (result.next()){
                flight = new Flight();
                flight.setId(result.getInt("id"));
                flight.setDeparture_date(result.getString("departure_date"));
                flight.setDeparture_time(result.getString("departure_time"));
                flight.setDestination(result.getString("destination"));
                flight.setId_airplane(result.getInt("id_airplane"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while loading the flight...");
        }

        this.database.disconnect();
        return flight;
    }

    public ArrayList<Flight> findByDate(String flightDate) {
        Connection connection = this.database.connect();
        ArrayList<Flight> flights = new ArrayList<>();

        try {
            String sql = "SELECT * FROM flights WHERE departure_date LIKE ? OR departure_time LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + flightDate + "%");
            preparedStatement.setString(2, "%" + flightDate + "%");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                Flight flight = new Flight();
                flight.setId(result.getInt("id"));
                flight.setDeparture_date(result.getString("departure_date"));
                flight.setDeparture_time(result.getString("departure_time"));
                flight.setDestination(result.getString("destination"));
                flight.setId_airplane(result.getInt("id_airplane"));
                flights.add(flight);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while listing the data...");
        }

        return flights;
    }

    public boolean update(Object object){
        return super.update("flights", object);
    }

    public boolean delete(int id){
        return super.delete("flights", id);
    }
}