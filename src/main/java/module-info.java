module com.example.bartekinz3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bartekinz3 to javafx.fxml;
    exports com.example.bartekinz3;
}