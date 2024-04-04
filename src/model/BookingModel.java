package model;

import database.Database;
import entity.Booking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingModel extends GeneralModel {

    private Database database;

    public BookingModel(){
        super();
        this.database = Database.getInstance();
    }

    public Object create(Object object) {
        ResultSet result = super.create("bookings", object);
        Booking booking = (Booking) object;

        try{
            while(result.next()){
                booking.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while creating the booking... " + e.getMessage());
        }

        this.database.disconnect();
        return booking;
    }

    public ArrayList findAll() {
        ResultSet result = super.findAll("bookings");
        ArrayList<Booking> bookings = new ArrayList<>();

        try{
            while(result.next()){
                Booking booking = new Booking();
                booking.setId(result.getInt("id"));
                booking.setBooking_date(result.getString("booking_date"));
                booking.setSeat(result.getString("seat"));
                booking.setId_passenger(result.getInt("id_passenger"));
                booking.setId_flight(result.getInt("id_flight"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while listing the booking... " + e.getMessage());
        }

        this.database.disconnect();
        return bookings;
    }

    public Object findById(int id){
        ResultSet result = super.findById("bookings", id);
        Booking booking = null;
        try{
            while (result.next()){
                booking = new Booking();
                booking.setId(result.getInt("id"));
                booking.setBooking_date(result.getString("booking_date"));
                booking.setSeat(result.getString("seat"));
                booking.setId_passenger(result.getInt("id_passenger"));
                booking.setId_flight(result.getInt("id_flight"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while loading the booking...");
        }

        this.database.disconnect();
        return booking;
    }

    public boolean isSeatAvailable(String date, int planeId, String seatId){
        Connection connection = this.database.connect();
        int taken = 0;
        int capacity = 1;
        boolean finalResult = true;

        try{
            String sql = "SELECT b.id, b.seat, a.capacity FROM bookings b " +
                            "INNER JOIN flights f ON f.id = b.id_flight " +
                            "INNER JOIN airplanes a ON a.id = f.id_airplane " +
                            "WHERE f.id_airplane = ? AND f.departure_date = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, planeId);
            preparedStatement.setString(2, date);
            preparedStatement.executeQuery();
            ResultSet result = preparedStatement.getResultSet();

            while (result.next()){
                taken++;
                String seat = result.getString("seat");
                if(capacity == 1) capacity = result.getInt("capacity");
                System.out.println("TAKEN SEAT -> " + seat);
                System.out.println("SEAT TO TAKE -> " + seatId);
                if(seat == seatId) {
                    System.out.println("compared true");
                    finalResult = false;
                };
            }

            if(taken == capacity) finalResult = false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while verifying the booking... " + e.getMessage());
            finalResult = false;
        }

        return finalResult;
    }

    public boolean update(Object object){
        return super.update("bookings", object);
    }

    public boolean delete(int id){
        return super.delete("bookings", id);
    }
}