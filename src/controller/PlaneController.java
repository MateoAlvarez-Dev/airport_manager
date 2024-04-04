package controller;

import entity.Plane;
import model.PlaneModel;

import javax.swing.*;
import java.util.ArrayList;

public class PlaneController {
    private final PlaneModel planeModel;

    public PlaneController(){
        this.planeModel = new PlaneModel();
    }

    public void findAll(){
        ArrayList<Plane> planes = this.planeModel.findAll();
        JOptionPane.showMessageDialog(null, getList(planes));
    }

    public void findById(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id to search:"));
        Plane plane = (Plane) this.planeModel.findById(id);
        if(plane != null){
            JOptionPane.showMessageDialog(null, "Plane Found: \n" + plane.getId() + " - " + plane.getModel() + " - " + plane.getCapacity());
        }else{
            JOptionPane.showMessageDialog(null, "Plane not found...");
        }
    }

    public String getList(ArrayList<Plane> list){
        String listString = "Plane list:\n";
        for(Plane plane : list){
            listString += plane.getId() + " - " + plane.getModel() + " - " + plane.getCapacity() + "\n";
        }
        return listString;
    }

    public void create(){
        Plane plane = new Plane();
        String model = JOptionPane.showInputDialog("Insert the plane model:");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Insert the plane capacity:"));

        plane.setModel(model);
        plane.setCapacity(capacity);

        plane = (Plane) planeModel.create(plane);

        if(plane != null){
            JOptionPane.showMessageDialog(null, "Plane Created!\n" + plane.getId() + " - " + plane.getModel() + " - " + plane.getCapacity());
        }else{
            JOptionPane.showMessageDialog(null, "Error, plane not created");
        }
    }

    public void update(){
        ArrayList<Plane> planes = this.planeModel.findAll();
        String planeList = this.getList(planes);
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(planeList+"Insert the id to update:"));
        Plane plane = (Plane) planeModel.findById(idToUpdate);

        if(plane != null){
            String model = JOptionPane.showInputDialog("Insert the new model:", plane.getModel());
            int capacity = Integer.parseInt(JOptionPane.showInputDialog("Insert the new capacity:", plane.getCapacity()));

            plane.setCapacity(capacity);
            plane.setModel(model);

            boolean isUpdated = planeModel.update(plane);
            if(!isUpdated) JOptionPane.showMessageDialog(null, "Error in the update :(");
            else JOptionPane.showMessageDialog(null, "Updated succesfully! :)");
        }
    }

    public void delete(){
        ArrayList<Plane> planes = this.planeModel.findAll();
        String planeList = this.getList(planes);
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog(planeList + "Insert the id to delete:"));
        Plane plane = (Plane) this.planeModel.findById(idToDelete);

        if(plane != null){
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the plane " + plane.getModel() + "?");
            if(confirmation == 0){
                boolean isDeleted = this.planeModel.delete(idToDelete);
                if(isDeleted) JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                else JOptionPane.showMessageDialog(null, "Error, cannot delete the element");
            }else{
                JOptionPane.showMessageDialog(null, "Operation cancelled");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Plane not found :(");
        }
    }
}
