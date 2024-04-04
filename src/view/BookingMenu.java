package view;

import controller.BookingController;

import javax.swing.*;

public class BookingMenu {

    private BookingController bookingController;

    public BookingMenu(){
        this.bookingController = new BookingController();
    }

    public void render(){
        while (true){
            String option;

            option = JOptionPane.showInputDialog("""
Welcome to the Airport Manager!
What you gonna do today?
1) Show all bookings
2) Search booking by ID
3) Create new booking
4) Delete a booking
5) Update a booking
6) Back
""");

            switch (option){
                case "1":
                    this.bookingController.findAll();
                    break;

                case "2":
                    this.bookingController.findById();
                    break;

                case "3":
                    this.bookingController.create();
                    break;

                case "4":
                    this.bookingController.delete();
                    break;

                case "5":
                    this.bookingController.update();
                    break;

                case "6":
                    return;
            }
        }
    }
}