module com.example.lab7java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab7java to javafx.fxml;
    exports com.example.lab7java;
}