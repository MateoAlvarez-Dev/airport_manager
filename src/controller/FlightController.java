package controller;

import entity.Flight;
import model.FlightModel;

import javax.swing.*;
import java.util.ArrayList;

public class FlightController {
    private final FlightModel flightModel;

    public FlightController(){
        this.flightModel = new FlightModel();
    }

    public void findAll(){
        ArrayList flights = this.flightModel.findAll();
        JOptionPane.showMessageDialog(null, getList(flights));
    }

    public void findById(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id to search:"));
        Flight flight = (Flight) this.flightModel.findById(id);
        if(flight != null){
            JOptionPane.showMessageDialog(null, "Flight Found: \n" + flight.getId() + " - "
                    + flight.getDestination() + " - "
                    + flight.getDeparture_date() + " - "
                    + flight.getDeparture_time() + " - "
                    + flight.getId_airplane());
        }else{
            JOptionPane.showMessageDialog(null, "Flight not found...");
        }
    }

    public String getList(ArrayList<Flight> list){
        String listString = "Flight list: \n";
        for(Flight flight : list){
            listString += flight.getId() + " - "
                    + flight.getDestination() + " - "
                    + flight.getDeparture_date() + " - "
                    + flight.getDeparture_time() + " - "
                    + flight.getId_airplane() + "\n";
        }
        return listString;
    }

    public void create(){
        Flight flight = new Flight();

        String destination = JOptionPane.showInputDialog("Insert the destination:");
        String departure_date = JOptionPane.showInputDialog("Insert the departure date in format AAAA-MM-DD");
        String departure_time = JOptionPane.showInputDialog("Insert the departure time in format HH:MM:SS");
        int id_airplane = Integer.parseInt(JOptionPane.showInputDialog("Insert the airplane id:"));

        flight.setDestination(destination);
        flight.setDeparture_date(departure_date);
        flight.setDeparture_time(departure_time);
        flight.setId_airplane(id_airplane);

        flight = (Flight) flightModel.create(flight);

        if(flight != null){
            JOptionPane.showMessageDialog(null, "Flight Created! " + flight.getId() + " - "
                    + flight.getDestination() + " - "
                    + flight.getDeparture_date() + " - "
                    + flight.getDeparture_time() + " - "
                    + flight.getId_airplane());
        }else{
            JOptionPane.showMessageDialog(null, "Error, flight not created");
        }
    }

    public void update(){
        ArrayList flights = this.flightModel.findAll();
        String flightList = this.getList(flights);
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(flightList+"Insert the id to update:"));
        Flight flight = (Flight) flightModel.findById(idToUpdate);

        if(flight != null){
            String destination = JOptionPane.showInputDialog("Insert the new destination:", flight.getDestination());
            String departure_date = JOptionPane.showInputDialog("Insert the new departure date in format AAAA-MM-DD", flight.getDeparture_date());
            String departure_time = JOptionPane.showInputDialog("Insert the new departure time in format HH:MM:SS", flight.getDeparture_time());
            int id_airplane = Integer.parseInt(JOptionPane.showInputDialog("Insert the new airplane id:", flight.getId_airplane()));

            flight.setDestination(destination);
            flight.setDeparture_date(departure_date);
            flight.setDeparture_time(departure_time);
            flight.setId_airplane(id_airplane);


            boolean isUpdated = flightModel.update(flight);
            if(!isUpdated) JOptionPane.showMessageDialog(null, "Error in the update :(");
            else JOptionPane.showMessageDialog(null, "Updated succesfully! :)");
        }
    }

    public void delete(){
        ArrayList flights = this.flightModel.findAll();
        String flightList = this.getList(flights);
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog(flightList + "Insert the id to delete:"));
        Flight flight = (Flight) this.flightModel.findById(idToDelete);

        if(flight != null){
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the flight " + flight.getId() + "?");
            if(confirmation == 0){
                boolean isDeleted = this.flightModel.delete(idToDelete);
                if(isDeleted) JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                else JOptionPane.showMessageDialog(null, "Error, cannot delete the element");
            }else{
                JOptionPane.showMessageDialog(null, "Operation cancelled");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Flight not found :(");
        }
    }
}
