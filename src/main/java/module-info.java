module com.example.parcial3 {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parcial3 to javafx.fxml;
    exports com.example.parcial3;
    exports com.example.parcial3.Models;
    opens com.example.parcial3.Models to javafx.fxml;
    exports com.example.parcial3.Controllers;
    opens com.example.parcial3.Controllers to javafx.fxml;
    exports com.example.parcial3.Repositories;
    opens com.example.parcial3.Repositories to javafx.fxml;
}