module com.mycompany.practicacrudusuarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.practicacrudusuarios to javafx.fxml;
    exports com.mycompany.practicacrudusuarios;
}
