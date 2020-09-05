package studentdatabaseapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import studentdatabaseapp.datamodel.Student;

public class DialogController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField courseTextField;

    @FXML
    public Student processResults() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String year = yearTextField.getText();
        String course = courseTextField.getText();

        return new Student(firstName, lastName, year, course);

    }
    @FXML
    public void editStudent(Student student) {
        student.setFirstName(firstNameTextField.getText());
        student.setLastName(lastNameTextField.getText());
        student.setSchoolYear(yearTextField.getText());
        student.setCourse(courseTextField.getText());
    }

    @FXML
    public void updateStudent(Student student) {
        student.setFirstName(firstNameTextField.getText());
        student.setLastName(lastNameTextField.getText());
        student.setSchoolYear(yearTextField.getText());
        student.setCourse(courseTextField.getText());
    }
}
