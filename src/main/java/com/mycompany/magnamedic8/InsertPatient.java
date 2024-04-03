package com.mycompany.magnamedic8;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertPatient {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/magnamedic";
        String username = "root";
        String password = "";
        Statement statement = null;
        ResultSet rs = null;
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, username, password);
            statement = conexion.createStatement();

            statement.executeUpdate("INSERT INTO PATIENTS (IDENTIFICATION, NAME, LAST_NAME, GENDER, DATE_OF_BIRTH, ADDRESS, CITY, TELEPHONE) VALUES('22222222','Laura', 'Vargas', 'FEMENINO', '2005-04-15', 'TV 4 # 15-12', 'Cauca', '3202020202')");

            rs = statement.executeQuery("SELECT * FROM patients");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " - " + rs.getInt("IDENTIFICATION") + " - " + rs.getString("NAME") + " - " + rs.getString("LAST_NAME") + " - " + rs.getString("ADDRESS"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertPatient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Cierre de recursos
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertPatient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
