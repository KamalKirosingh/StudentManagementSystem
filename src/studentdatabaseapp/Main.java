package studentdatabaseapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

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

        System.out.println("Enter number of new students to enrol");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        Student[] students = new Student[size];

        for(int i = 0; i < size; i++) {
            students[i] = new Student();
            students[i].enrol();
            students[i].payTuition();
            System.out.println(students[i]);
        }

    }
}
