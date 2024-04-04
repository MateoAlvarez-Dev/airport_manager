package model;

import database.CRUD;
import database.Database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GeneralModel implements CRUD {

    private Database database;

    public GeneralModel(){
        this.database = Database.getInstance();
    }

    @Override
    public ResultSet findAll(String tableName) {
        Connection connection = this.database.connect();
        ResultSet result = null;

        try {
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while listing all data...");
        }

        return result;
    }

    @Override
    public ResultSet findById(String tableName, int id) {
        Connection connection = this.database.connect();
        ResultSet result = null;

        try {
            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while listing the data...");
        }

        return result;
    }

    @Override
    public boolean delete(String tableName, int id) {
        Connection connection = this.database.connect();
        boolean wasDeleted = false;

        try {
            String sql = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0) wasDeleted = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while deleting the data...");
        }

        return wasDeleted;
    }

    @Override
    public ResultSet create(String tableName, Object object) {
        Connection connection = this.database.connect();
        ArrayList<String> fields = this.getEntityFields(object);
        ResultSet result = null;

        try {

            String[] newFieldsArray = fields.toString()
                    .replace("[", "")
                    .replace("]", "").split(",");

            String[] fieldsFormatted = getFormattedFields(newFieldsArray, 1);

            String sql = "INSERT INTO " + tableName + "(" + fieldsFormatted[0] + " ) VALUES (" + fieldsFormatted[1] + ")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while creating the data...");
        }

        return result;
    }

    @Override
    public boolean update(String tableName, Object object) {
        Connection connection = this.database.connect();
        ArrayList<String> fields = this.getEntityFields(object);
        boolean result = false;

        try{
            String[] newFieldsArray = fields.toString()
                    .replace("[", "")
                    .replace("]", "").split(",");

            String[] fieldsFormatted = getFormattedFields(newFieldsArray, 1);

            String sql = "UPDATE " + tableName + "(" + fieldsFormatted[0] + " ) VALUES (" + fieldsFormatted[1] + ")";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0){
                result = true;
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has occurred while listing all data...");
        }

        return result;
    }

    // UTILITIES ===============

    public String[] getFormattedFields(String[] fields, int startIndex){
        String fieldList = "";
        String valueList = "";
        for(int i = startIndex; i < fields.length; i++){
            fieldList += fields[i].split("-")[0];
            String[] value = fields[i].split("-")[1].split(":");

            if(value[0].equals("String")) valueList += "'" + value[1] + "'";
            else valueList += value[1];

            if(i != fields.length - 1){
                fieldList += ",";
                valueList += ",";
            };
        }
        return new String[]{fieldList, valueList};
    }

    public ArrayList<String> getEntityFields(Object object){
        Method[] methods = object.getClass().getMethods();
        ArrayList<String> getterValues;

        try{

            String[] methodNames = new String[0];
            for(Method m : methods){
                if(!m.getName().equals("ExportGetMethodNames")) continue;
                methodNames = (String[]) m.invoke(object, null);
            }

            getterValues = invokeGetterMethods(object, methodNames);

            return getterValues;

        } catch (InvocationTargetException e) {
            System.out.println("Error on the invoke");
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access to the method");
        }

        return null;
    }

    private ArrayList<String> invokeGetterMethods(Object c, String[] getterMethodsNames){
        ArrayList<String> getterValues = new ArrayList<>();

        try{
            Method[] methods = c.getClass().getMethods();
            for(Method m : methods){
                if(!ArrayIncludes(m.getName(), getterMethodsNames)) continue;
                String fieldName = m.getName().replaceAll("get", "").toLowerCase();

                String fieldDataType = m.getReturnType().toString();
                String[] fieldDataTypeSplitted = fieldDataType.split("\\.");
                String finalDataType = fieldDataTypeSplitted[fieldDataTypeSplitted.length - 1];

                getterValues.add(fieldName + "-" + finalDataType + ":" + m.invoke(c, null));
            }
        } catch (InvocationTargetException e) {
            getterValues = null;
            System.out.println("Error on the invoke");
        } catch (IllegalAccessException e) {
            getterValues = null;
            System.out.println("Cannot access to the method");
        }

        return getterValues;
    }

    private boolean ArrayIncludes(String value, String[] arr){
        for(String str : arr){
            if(str.contains(value)) return true;
        }
        return false;
    }
}