module com.mycompany.practicacrudusuarios {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.practicacrudusuarios to javafx.fxml;
    exports com.mycompany.practicacrudusuarios;
}
