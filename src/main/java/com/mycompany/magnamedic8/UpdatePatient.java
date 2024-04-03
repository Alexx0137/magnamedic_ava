package com.mycompany.magnamedic8;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatePatient {
    
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

            int rowsAffected = statement.executeUpdate("UPDATE PATIENTS SET NAME = 'Alexander', ADDRESS = 'calle 40 norte', CITY = 'Soacha' WHERE ID = 1");
            System.out.println("Filas afectadas por la actualización: " + rowsAffected);

            rs = statement.executeQuery("SELECT * FROM patients WHERE ID = 1");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + ":" + rs.getInt("IDENTIFICATION") + " - " + rs.getString("NAME") + " " + rs.getString("LAST_NAME") + " - " + rs.getString("ADDRESS"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdatePatient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
                 Logger.getLogger(UpdatePatient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
