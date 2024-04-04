package view;

import javax.swing.*;

public class MainMenu {

    private PlaneMenu planeMenu;

    public MainMenu(){
        this.planeMenu = new PlaneMenu();
    }

    public void render(){
        while (true){
            String option;

            option = JOptionPane.showInputDialog("""
                Welcome to the Airport Manager!
                What you gonna do today?
                    1) Plane Manager
                    6) Exit
                """);

            switch (option){
                case "1":
                    this.planeMenu.render();
                    break;

                case "6":
                    JOptionPane.showMessageDialog(null, "Good Bye!");
                    return;
            }
        }
    }
}
