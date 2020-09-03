package studentdatabaseapp;

import java.util.Scanner;

public class Student {

    private String firstName;
    private String lastName;
    private String gradeYear;
    private String studentID;
    private String courses;
    private int tuitionBalance = 0;
    private static int costOfCourse = 600;
    private static int id = 1000;

    public Student() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter student first name: ");
        this.firstName = in.nextLine();

        System.out.println("Enter student last name: ");
        this.lastName = in.nextLine();

        System.out.println("1 - Year 7\n2 - Year 8\n3 - Year 9\n4 " +
                "- Year 10\n5 - Year 11\nEnter year: ");
        this.gradeYear = in.nextLine();

        setStudentID();

        System.out.println(firstName + " " + lastName + " enrolling to Year Level "
                + gradeYear + " " + studentID);

    }
//    Generate an ID
    private void setStudentID() {
        id++;
        this.studentID = gradeYear + "" + id;
    }

//    Enrol in courses
    public void enrol() {
//        Get inside a loop, user hits 0
        boolean flag = false;

        while(!flag) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter course to enrol (Q to quit): ");
            String course = scanner.nextLine();

            if (!course.equalsIgnoreCase("Q")) {
                courses += "\n" + course;
                tuitionBalance += costOfCourse;
            } else {
                flag = true;
            }
        }
        System.out.println("ENROLLED IN: " + courses);
        System.out.println("TUITION BALANCE: " + tuitionBalance);
    }
//test

}
