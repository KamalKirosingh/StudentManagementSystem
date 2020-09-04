package studentdatabaseapp.datamodel;

public class Student {

    private String firstName;
    private String lastName;
    private String schoolYear;
    private String course;

//    Show status
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nYear: " + schoolYear +
                "\nCourse: " + course;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
