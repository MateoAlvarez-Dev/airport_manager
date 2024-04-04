package view;

import controller.FlightController;

import javax.swing.*;

public class FlightMenu {

    private FlightController flightController;

    public FlightMenu(){
        this.flightController = new FlightController();
    }

    public void render(){
        while (true){
            String option;

            option = JOptionPane.showInputDialog("""
Welcome to the Airport Manager!
What you gonna do today?
1) Show all flights
2) Search flight by ID
3) Create new flight
4) Delete a flight
5) Update a flight
6) Back
""");

            switch (option){
                case "1":
                    this.flightController.findAll();
                    break;

                case "2":
                    this.flightController.findById();
                    break;

                case "3":
                    this.flightController.create();
                    break;

                case "4":
                    this.flightController.delete();
                    break;

                case "5":
                    this.flightController.update();
                    break;

                case "6":
                    return;
            }
        }
    }
}