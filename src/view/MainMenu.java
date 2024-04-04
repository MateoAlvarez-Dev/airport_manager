package view;

import javax.swing.*;

public class MainMenu {

    private final PlaneMenu planeMenu;
    private final PassengerMenu passengerMenu;
    private final FlightMenu flightMenu;
    private final BookingMenu bookingMenu;

    public MainMenu(){
        this.planeMenu = new PlaneMenu();
        this.passengerMenu = new PassengerMenu();
        this.flightMenu = new FlightMenu();
        this.bookingMenu = new BookingMenu();
    }

    public void render(){
        while (true){
            String option;

            option = JOptionPane.showInputDialog("""
                Welcome to the Airport Manager!
                What you gonna do today?
                    1) Plane Manager
                    2) Passenger Manager
                    3) Flight Manager
                    4) Booking Manager
                    6) Exit
                """);

            switch (option){
                case "1":
                    this.planeMenu.render();
                    break;

                case "2":
                    this.passengerMenu.render();
                    break;

                case "3":
                    this.flightMenu.render();
                    break;

                case "4":
                    this.bookingMenu.render();
                    break;

                case "6":
                    JOptionPane.showMessageDialog(null, "Good Bye!");
                    return;
            }
        }
    }
}
