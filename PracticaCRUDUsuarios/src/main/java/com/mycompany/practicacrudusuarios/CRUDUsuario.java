/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicacrudusuarios;

/**
 *
 * @author am199
 */
//import com.mysql.jdbc.PreparedStatement;  NO ES NECESARIO AHORA
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utilidades.convertirSHA256;
import java.sql.PreparedStatement;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Rocio
 */
public class CRUDUsuario {

    public static void insertarUsuario(Connection conexion, Usuario usu) {
        PreparedStatement ps;
        String cadenaCodificada;
        try {

            cadenaCodificada = convertirSHA256.encriptarSHA256(usu.getPass());
            String sql = "Insert into usuarios values(?,?,?,?)";
            ps = (PreparedStatement) conexion.prepareStatement(sql);

            ps.setString(1, usu.getCorreo_electronico());
            ps.setString(2, cadenaCodificada);
            ps.setDouble(3, usu.getDescuentos());
            ps.setBoolean(4, usu.getPremium());

            ps.executeUpdate();
            System.out.println("Usuario Insertado CORRECTAMENTE");

        } catch (SQLException ex) {
            System.out.print("ERROR AL INSERTAR");
        }

    }

    public static void buscarUsuario(Connection conexion, String correo, TextField campo2, TextField campo3, RadioButton oprSi, RadioButton oprNo) {

        PreparedStatement ps;
        ResultSet rs;
        String des="";
        boolean encontrado = false;
        boolean prem = false;
        try {
            String SQL = "SELECT * FROM usuarios WHERE correo_electronico = ? ;";
            ps = (PreparedStatement) conexion.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            
            while(rs.next()){
                encontrado = true;
                des = String.valueOf(rs.getDouble("descuentos"));
                if (encontrado) {
                    campo2.setText(rs.getString("pass"));
                    campo3.setText(des);
                    prem = rs.getBoolean("premium");
                    if(prem){
                        oprSi.setSelected(true);
                        oprNo.setSelected(false);
                    }else{
                        oprSi.setSelected(false);
                        oprNo.setSelected(true);
                    }
                } else {
                    System.out.println("Usuario NO ENCONTRADO");
                }
            }          
            

        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }

    }

    public static void totalIngresos(Connection conexion) {

        PreparedStatement ps;
        ResultSet rs;
        double total_descuentos = 0;
        double total_ingresos = 0;

        try {
            System.out.println("ingresos");
            String SQL = "SELECT * FROM usuarios ;";
            ps = (PreparedStatement) conexion.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();

            while (rs.next()) {

                total_descuentos += rs.getDouble("descuentos");
                if (rs.getBoolean("premium")) {
                    total_ingresos += 35.5;
                } else {
                    total_ingresos += 20.5;
                }

            }

            System.out.println("Total Ingresos : " + (total_ingresos - total_descuentos));

        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

}
