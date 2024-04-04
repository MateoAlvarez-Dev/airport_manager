package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private static Database instance;

    private Database(){};

    public Connection connect(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    ConfigDB.URL.getValue(),
                    ConfigDB.USERNAME.getValue(),
                    ConfigDB.PASSWORD.getValue()
            );

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR -> " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ERROR -> " + e.getMessage());
        };
        return connection;
    }

    public void disconnect(){
        try{
            if(connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("ERROR -> " + e.getMessage());
        }
    }

    public static Database getInstance(){
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


}
