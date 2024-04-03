package com.mycompany.magnamedic8;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeletePatient {
    
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

            int rowsAffected = statement.executeUpdate("DELETE FROM PATIENTS WHERE ID=2");
            System.out.println("Filas afectadas por la eliminación: " + rowsAffected);

            rs = statement.executeQuery("SELECT * FROM patients");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " - " + rs.getInt("IDENTIFICATION") + " - " + rs.getString("NAME") + " - " + rs.getString("LAST_NAME") + " - " + rs.getString("ADDRESS"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeletePatient.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(DeletePatient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
