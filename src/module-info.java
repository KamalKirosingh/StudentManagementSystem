module StudentManagementSystem {
    requires javafx.fxml;
    requires javafx.controls;
    requires kotlin.stdlib;
    requires java.xml;

    opens studentdatabaseapp;
    opens studentdatabaseapp.datamodel;
}