package com.mycompany.practicacrudusuarios;

import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    public TextField campo1;
    
    @FXML
    private TextField campo2;
    
    @FXML
    private TextField campo3;
    
    @FXML
    private RadioButton oprSi;
    @FXML
    private RadioButton oprNo;
    
    @FXML
    public static Connection con ;
    
    @FXML
    public static void WindowEvent(){
        try{
             con = ConexionDB.openConnection();
              System.out.println("Conectandome a la BD Correctamente");
        }catch(Exception ex)
         {
             System.out.println(ex.toString());
            
         }
    }
    
    @FXML
    private void Salir() throws IOException {
        System.exit(0);
    }
    
    @FXML
    private void Limpiar() throws IOException {
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
    }
    
    @FXML
    private void Insertar() throws IOException {
        boolean auxPremium;
        if(this.oprSi.isSelected())
        
            auxPremium=true;
        else
            auxPremium=false;
        
        Usuario usu1 = new Usuario(auxPremium,campo1.getText(),campo2.getText(),Integer.parseInt(campo3.getText()));
     
        CRUDUsuario.insertarUsuario(con, usu1);
    }
    
     @FXML
    private void Buscar() throws IOException {
         CRUDUsuario.buscarUsuario(con, this.campo1.getText(), campo2, campo3, oprSi, oprNo);
    }
    
     @FXML
    private void Ingresos() throws IOException {
         CRUDUsuario.totalIngresos(con);
    }
}
