package studentdatabaseapp.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Student {

    private SimpleStringProperty firstName = new SimpleStringProperty("");
    private SimpleStringProperty lastName = new SimpleStringProperty("");
    private SimpleStringProperty schoolYear = new SimpleStringProperty("");
    private SimpleStringProperty course = new SimpleStringProperty("");

    public Student() {
    }

    public Student(String firstName, String lastName, String schoolYear, String course) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.schoolYear.set(schoolYear);
        this.course.set(course);
    }

    //    Show status
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nYear: " + schoolYear +
                "\nCourse: " + course;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getSchoolYear() {
        return schoolYear.get();
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear.set(schoolYear);
    }

    public String getCourse() {
        return course.get();
    }

    public void setCourse(String course) {
        this.course.set(course);
    }
}
