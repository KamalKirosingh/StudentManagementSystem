package studentdatabaseapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Student Management System");
//        primaryStage.setScene(new Scene(root, 600, 275));
//        primaryStage.show();
//    }


    public static void main(String[] args) {
//        launch(args);

        Student stu1 = new Student();
        stu1.enrol();
    }
}