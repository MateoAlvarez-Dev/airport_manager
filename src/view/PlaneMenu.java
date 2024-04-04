package view;

import controller.PlaneController;

import javax.swing.*;

public class PlaneMenu {

    private PlaneController planeController;

    public PlaneMenu(){
        this.planeController = new PlaneController();
    }

    public void render(){
        while (true){
            String option;

            option = JOptionPane.showInputDialog("""
                Welcome to the Airport Manager!
                What you gonna do today?
                    1) Show all planes
                    2) Search plane by ID
                    3) Create new plane
                    4) Delete a plane
                    5) Update a plane
                    6) Exit
                """);

            switch (option){
                case "1":
                    this.planeController.findAll();
                    break;

                case "2":
                    this.planeController.findById();
                    break;

                case "3":
                    this.planeController.create();
                    break;

                case "4":
                    this.planeController.delete();
                    break;

                case "5":
                    this.planeController.update();
                    break;

                case "6":
                    return;
            }
        }
    }
}
