package view;

import javax.swing.*;

public class MainMenu {

    private PlaneMenu planeMenu;
    private PassengerMenu passengerMenu;
    private FlightMenu flightMenu;

    public MainMenu(){
        this.planeMenu = new PlaneMenu();
        this.passengerMenu = new PassengerMenu();
        this.flightMenu = new FlightMenu();
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

                case "6":
                    JOptionPane.showMessageDialog(null, "Good Bye!");
                    return;
            }
        }
    }
}
