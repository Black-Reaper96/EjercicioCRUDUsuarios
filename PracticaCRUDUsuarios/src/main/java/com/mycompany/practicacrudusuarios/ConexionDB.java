/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicacrudusuarios;
import java.sql.*;
/**
 *
 * @author am199
 */
public class ConexionDB {
    public static Connection openConnection() {
        
        Connection con =null;
        //HAy que añadir la zona sino da ERROR en la conexion
        String url="jdbc:mysql://localhost:3306/db_cliente?serverTimezone=UTC"; 
        String user="root";
        String pass="CursoDAM_2223";
        try {
        // Cargar el driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");// la otra que se ultilizaba en el ejemplo anterior esta OBSOLETA
        
            // Obtener la conexión
            con= DriverManager.getConnection(url,user,pass);
            
            
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Excepción: " + cE.toString());
        }
        return con;
    }
    
    public void closeConnection(Connection con) throws SQLException{
        
        try{
            //Cierra la conexión
            con.close();
        }catch(SQLException e){
            System.out.println("SQL Exception: "+e.toString());
        }//Cierra try-catch

    }//Cierra closeConnection
}
    

