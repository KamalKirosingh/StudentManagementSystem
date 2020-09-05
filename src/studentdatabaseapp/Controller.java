package studentdatabaseapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import studentdatabaseapp.datamodel.Student;
import studentdatabaseapp.datamodel.StudentData;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private BorderPane mainPanel;

    private StudentData data;

    public void initialize() {
        data = new StudentData();
        data.loadStudents();
        studentsTable.setItems(data.getStudents());
    }

    @FXML
    public void showNewStudentDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Student");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("studentdialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            Student newStudent = controller.processResults();
            data.addStudent(newStudent);
            data.saveStudent();
        }
    }

    @FXML
    public void removeStudentDialog() {
        Student deleteStudent = studentsTable.getSelectionModel().getSelectedItem();

        if (deleteStudent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Student Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the student you want to edit.");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Student");
            alert.setHeaderText("Delete Student: " + deleteStudent.getFirstName() + " " + deleteStudent.getLastName());
            alert.setContentText("Are you sure? Press OK to confirm, or Cancel to back out");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                data.deleteStudent(deleteStudent);
                data.saveStudent();
            }
        }
    }
    @FXML
    public void editStudentDialog() {
        Student editStudent = studentsTable.getSelectionModel().getSelectedItem();

        if (editStudent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Student Selected");
            alert.setHeaderText("Edit Student");
            alert.setContentText("Please select the student you want to edit.");
            alert.showAndWait();
            return;
        } else {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainPanel.getScene().getWindow());
            dialog.setTitle("Edit Student");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("studentdialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());

            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            DialogController controller = fxmlLoader.getController();
           controller.editStudent(editStudent);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                controller.updateStudent(editStudent);
                data.saveStudent();
            }
        }
    }
}
