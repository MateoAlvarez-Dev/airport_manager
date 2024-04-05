package controller;

import entity.Booking;
import entity.Flight;
import entity.Passenger;
import model.BookingModel;
import model.FlightModel;
import model.PassengerModel;

import javax.swing.*;
import java.util.ArrayList;

public class BookingController {
    private final BookingModel bookingModel;
    private final PassengerModel passengerModel;
    private final PassengerController passengerController;
    private final FlightModel flightModel;
    private final FlightController flightController;

    public BookingController(){
        this.bookingModel = new BookingModel();
        this.passengerModel = new PassengerModel();
        this.passengerController = new PassengerController();
        this.flightModel = new FlightModel();
        this.flightController = new FlightController();
    }

    public void findAll(){
        ArrayList bookings = this.bookingModel.findAll();
        JOptionPane.showMessageDialog(null, getList(bookings));
    }

    public void findById(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id to search:"));
        Booking booking = (Booking) this.bookingModel.findById(id);
        if(booking != null){
            JOptionPane.showMessageDialog(null, "Booking Found: \n" + booking.getId() + " - "
                    + booking.getBooking_date() + " - "
                    + booking.getSeat() + " - "
                    + booking.getId_passenger() + " - "
                    + booking.getId_flight() + "\n"
            );
        }else{
            JOptionPane.showMessageDialog(null, "Booking not found...");
        }
    }

    public String getList(ArrayList<Booking> list){
        String listString = "Booking list: \n";
        for(Booking booking : list){
            Passenger passenger = (Passenger) this.passengerModel.findById(booking.getId_passenger());
            Flight flight = (Flight) this.flightModel.findById(booking.getId_flight());
            listString += booking.getId() + " - "
                    + booking.getBooking_date() + " - "
                    + booking.getSeat() + " - "
                    + passenger.getName() + " " + passenger.getLast_name() + " - "
                    + flight.getDestination() + "\n";
        }
        return listString;
    }

    public void create(){
        String passengers = this.passengerController.getList(this.passengerModel.findAll());
        String flights = this.flightController.getList(this.flightModel.findAll());

        Booking booking = new Booking();
        String seat = JOptionPane.showInputDialog("Insert the plane seat:");
        int id_passenger = Integer.parseInt(JOptionPane.showInputDialog(passengers + "Insert the passenger id:"));
        int id_flight = Integer.parseInt(JOptionPane.showInputDialog(flights + "Insert the flight id:"));

        Flight flight = (Flight) this.flightModel.findById(id_flight);

        if(flight == null){
            JOptionPane.showMessageDialog(null, "That flight does not exist");
            return;
        }

        int isSeatAvailable = this.bookingModel.isSeatAvailable(flight.getDeparture_date(), flight.getId_airplane(), seat);

        if(isSeatAvailable == 1){
            JOptionPane.showMessageDialog(null, "The airplane is full, sorry");
        }else if(isSeatAvailable == 2){
            JOptionPane.showMessageDialog(null, "Sorry but the seat " + seat + " is not available.");
        }else if(isSeatAvailable == 3){
            JOptionPane.showMessageDialog(null, "Internal error");
        }else{
            booking.setSeat(seat);
            booking.setId_passenger(id_passenger);
            booking.setId_flight(id_flight);
            booking.setBooking_date(flight.getDeparture_date());

            booking = (Booking) bookingModel.create(booking);

            if(booking != null){
                JOptionPane.showMessageDialog(null, "Booking Created! \n" + booking.getId() + " - "
                        + booking.getBooking_date() + " - "
                        + booking.getSeat() + " - "
                        + booking.getId_passenger() + " - "
                        + booking.getId_flight() + "\n"
                );
            }else{
                JOptionPane.showMessageDialog(null, "Error, booking not created");
            }
        }
    }

    public void update(){
        ArrayList bookings = this.bookingModel.findAll();
        String bookingList = this.getList(bookings);
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(bookingList+"Insert the id to update:"));
        Booking booking = (Booking) bookingModel.findById(idToUpdate);

        if(booking != null){
            String seat = JOptionPane.showInputDialog("Insert the new plane seat:", booking.getSeat());
            int id_passenger = Integer.parseInt(JOptionPane.showInputDialog("Insert the new passenger id:", booking.getId_passenger()));
            int id_flight = Integer.parseInt(JOptionPane.showInputDialog("Insert the new flight id:", booking.getId_flight()));

            booking.setSeat(seat);
            booking.setId_passenger(id_passenger);
            booking.setId_flight(id_flight);

            boolean isUpdated = bookingModel.update(booking);
            if(!isUpdated) JOptionPane.showMessageDialog(null, "Error in the update :(");
            else JOptionPane.showMessageDialog(null, "Updated succesfully! :)");
        }
    }

    public void delete(){
        ArrayList bookings = this.bookingModel.findAll();
        String bookingList = this.getList(bookings);
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog(bookingList + "Insert the id to delete:"));
        Booking booking = (Booking) this.bookingModel.findById(idToDelete);

        if(booking != null){
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the booking " + booking.getId() + "?");
            if(confirmation == 0){
                boolean isDeleted = this.bookingModel.delete(idToDelete);
                if(isDeleted) JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                else JOptionPane.showMessageDialog(null, "Error, cannot delete the element");
            }else{
                JOptionPane.showMessageDialog(null, "Operation cancelled");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Booking not found :(");
        }
    }
}