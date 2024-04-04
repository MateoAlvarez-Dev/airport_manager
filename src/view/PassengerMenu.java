package view;

import controller.PassengerController;

import javax.swing.*;

public class PassengerMenu {

    private PassengerController passengerController;

    public PassengerMenu(){
        this.passengerController = new PassengerController();
    }

    public void render(){
        while (true){
            String option;

            option = JOptionPane.showInputDialog("""
Welcome to the Airport Manager!
What you gonna do today?
1) Show all passengers
2) Search passenger by ID
3) Create new passenger
4) Delete a passenger
5) Update a passenger
6) Back
""");

            switch (option){

                case "1":
                    this.passengerController.findAll();
                    break;

                case "2":
                    this.passengerController.findById();
                    break;

                case "3":
                    this.passengerController.create();
                    break;

                case "4":
                    this.passengerController.delete();
                    break;

                case "5":
                    this.passengerController.update();
                    break;

                case "6":
                    return;

            }
        }
    }
}