package controller;

import entity.Passenger;
import entity.Plane;
import model.PassengerModel;

import javax.swing.*;
import java.util.ArrayList;

public class PassengerController {
    private final PassengerModel passengerModel;

    public PassengerController(){
        this.passengerModel = new PassengerModel();
    }

    public void findAll(){
        ArrayList passengers = this.passengerModel.findAll();
        JOptionPane.showMessageDialog(null, getList(passengers));
    }

    public void findById(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id to search:"));
        Passenger passenger = (Passenger) this.passengerModel.findById(id);
        if(passenger != null){
            JOptionPane.showMessageDialog(null, "Passenger Found: \n" + passenger.getId() + " - " + passenger.getName() + " - " + passenger.getLast_name()+ " - " + passenger.getIdentity());
        }else{
            JOptionPane.showMessageDialog(null, "Passenger not found...");
        }
    }

    public void findByName(){
        String passengerName = JOptionPane.showInputDialog("Insert the name to search:");
        ArrayList<Passenger> passengers = this.passengerModel.findByName(passengerName);

        if(passengers.isEmpty()){
            JOptionPane.showMessageDialog(null, "Passenger not found");
        }else{
            String passengerList = this.getList(passengers);
            JOptionPane.showMessageDialog(null, passengerList);
        }

    }

    public String getList(ArrayList<Passenger> list){
        String listString = "Passenger list: \n";
        for(Passenger passenger : list){
            listString += passenger.getId() + " - " + passenger.getName() + " - " + passenger.getLast_name()+ " - " + passenger.getIdentity() + "\n";
        }
        return listString;
    }

    public void create(){
        Passenger passenger = new Passenger();
        String name = JOptionPane.showInputDialog("Insert the passenger name:");
        String last_name = JOptionPane.showInputDialog("Insert the passenger last name:");
        String identity = JOptionPane.showInputDialog("Insert the passenger identity:");

        passenger.setName(name);
        passenger.setLast_name(last_name);
        passenger.setIdentity(identity);

        passenger = (Passenger) passengerModel.create(passenger);

        if(passenger != null){
            JOptionPane.showMessageDialog(null, "Passenger Created! \n" + passenger.getId() + " - " + passenger.getName() + " - " + passenger.getLast_name()+ " - " + passenger.getIdentity());
        }else{
            JOptionPane.showMessageDialog(null, "Error, passenger not created");
        }
    }

    public void update(){
        ArrayList passengers = this.passengerModel.findAll();
        String passengerList = this.getList(passengers);
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(passengerList+"Insert the id to update:"));
        Passenger passenger = (Passenger) passengerModel.findById(idToUpdate);

        if(passenger != null){
            String name = JOptionPane.showInputDialog("Insert the new passenger name:", passenger.getName());
            String last_name = JOptionPane.showInputDialog("Insert the new passenger last name:", passenger.getLast_name());
            String identity = JOptionPane.showInputDialog("Insert the new passenger identity:", passenger.getIdentity());

            passenger.setName(name);
            passenger.setLast_name(last_name);
            passenger.setIdentity(identity);

            boolean isUpdated = passengerModel.update(passenger);
            if(!isUpdated) JOptionPane.showMessageDialog(null, "Error in the update :(");
            else JOptionPane.showMessageDialog(null, "Updated succesfully! :)");
        }
    }

    public void delete(){
        ArrayList passengers = this.passengerModel.findAll();
        String passengerList = this.getList(passengers);
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog(passengerList + "Insert the id to delete:"));
        Passenger passenger = (Passenger) this.passengerModel.findById(idToDelete);

        if(passenger != null){
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the passenger " + passenger.getName() + "?");
            if(confirmation == 0){
                boolean isDeleted = this.passengerModel.delete(idToDelete);
                if(isDeleted) JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                else JOptionPane.showMessageDialog(null, "Error, cannot delete the element");
            }else{
                JOptionPane.showMessageDialog(null, "Operation cancelled");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Passenger not found :(");
        }
    }
}