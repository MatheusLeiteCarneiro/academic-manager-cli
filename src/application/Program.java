package application;

import model.entities.Course;
import model.entities.Student;
import model.exceptions.EnrollmentException;
import model.exceptions.NonExistentIdException;
import model.services.AcademicService;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    private static int studentIdCount = 1;
    private static int courseIdCount = 1;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        AcademicService academicService = new AcademicService();
        System.out.println("Welcome to the academic manager system!");

        int operation = -1;
        while (operation != 0) {
            printMenu();
            operation = sc.nextInt();
            sc.nextLine();
            System.out.println();
            try {

                switch (operation) {
                    case 1:
                        handleAddStudent(sc, academicService);
                        break;
                    case 2:
                        handleAddCourse(sc, academicService);
                        break;
                    case 3:
                        handleDeleteStudent(sc, academicService);
                        break;
                    case 4:
                        handleDeleteCourse(sc, academicService);
                        break;
                    case 5:
                        enrollStudentInCourse(sc, academicService);
                        break;
                    case 6:
                        removeStudentFromACourse(sc, academicService);
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
            }
            catch (NonExistentIdException | EnrollmentException e){
                System.out.println(e.getMessage());
                pressEnterToContinue(sc);
            }
        }

        sc.close();
    }


    private static void printMenu(){
        System.out.println("\n--- Operations Menu ---\n");

        System.out.println("-- Records Managements --");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Delete Student");
        System.out.println("4. Delete Course");

        System.out.println("\n-- Enrollments --");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. Remove Student from Course");

        System.out.println("\n-- Reports --");
        System.out.println("7. View Student's Courses");
        System.out.println("8. View Course's Students");
        System.out.println("9. List All Students");
        System.out.println("10. List All Courses");

        System.out.println("\n-- System --");
        System.out.println("0. Exit");

        System.out.print("\nChoose an option: ");
    }

    private static Student pickStudentById(Scanner sc, AcademicService academicService) throws NonExistentIdException {
        System.out.print("Type the student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        return academicService.findAndVerifyStudentId(studentId);
    }

    private static Course pickCourseById(Scanner sc, AcademicService academicService) throws NonExistentIdException{
        System.out.print("Type the course ID: ");
        int courseId = sc.nextInt();
        sc.nextLine();
        return academicService.findAndVerifyCourseId(courseId);
    }

    private static boolean confirm(Scanner sc){
        int confirmationNumber = -1;
        while (true) {
            System.out.print("--Press 1-to confirm/0-to cancel: ");
            confirmationNumber = sc.nextInt();
            sc.nextLine();
            if(confirmationNumber == 0){
                System.out.println("Operation canceled!");
                return false;
            }
            if(confirmationNumber == 1){
                return true;
            }
            System.out.println("--Invalid digit");
        }
    }

    private static void pressEnterToContinue(Scanner sc){
        System.out.print("--Press ENTER to continue");
        sc.nextLine();
    }

    private static void handleAddStudent(Scanner sc, AcademicService academicService){
        System.out.println("Student data:");
        System.out.print("Full name: ");
        String studentName = sc.nextLine();
        Student newStudent = new Student(studentIdCount, studentName);
        academicService.addStudent(newStudent);
        System.out.println("Student: "+ newStudent +" successfully registered!");
        studentIdCount ++;
        pressEnterToContinue(sc);
    }

    private static void handleAddCourse(Scanner sc, AcademicService academicService){
        System.out.println("Course data:");
        System.out.print("Name: ");
        String courseName = sc.nextLine();
        Course newCourse = new Course(courseIdCount, courseName);
        academicService.addCourse(newCourse);
        System.out.println("Course: " + newCourse + " successfully registered!");
        courseIdCount ++;
        pressEnterToContinue(sc);
    }

    private static void handleDeleteStudent(Scanner sc, AcademicService academicService) throws NonExistentIdException {
        Student student = pickStudentById(sc, academicService);
        System.out.println("Delete student:" + student);
        boolean confirmation = confirm(sc);
        if(confirmation){
            academicService.deleteAStudent(student);
            System.out.println("Student successfully deleted!");
        }
        pressEnterToContinue(sc);
    }

    private static void handleDeleteCourse(Scanner sc, AcademicService academicService) throws NonExistentIdException {
        Course course = pickCourseById(sc, academicService);
        System.out.println("Delete student:" + course);
        boolean confirmation = confirm(sc);
        if(confirmation){
            academicService.deleteACourse(course);
            System.out.println("Course successfully deleted!");
        }
        else{
            System.out.println("Operation canceled!");
        }
        pressEnterToContinue(sc);
    }

    private static void enrollStudentInCourse(Scanner sc, AcademicService academicService)throws NonExistentIdException{
        Student student = pickStudentById(sc, academicService);
        Course course = pickCourseById(sc, academicService);
        academicService.enrollStudent(student, course);
        System.out.println("Student successfully enrolled in course!");
        pressEnterToContinue(sc);
    }

    private static void removeStudentFromACourse(Scanner sc, AcademicService academicService) throws NonExistentIdException, EnrollmentException {
        Student student = pickStudentById(sc, academicService);
        Course course = pickCourseById(sc, academicService);
        academicService.verifyIfStudentIsInCourse(student, course);
        boolean confirmation = confirm(sc);
        if(confirmation){
            academicService.removeAStudentFromACourse(student, course);
            System.out.println("Student successfully removed from the Course!");
        }
        pressEnterToContinue(sc);
    }
}
