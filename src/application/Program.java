package application;

import model.entities.Student;
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
        while (operation != 0){
            printMenu();
            operation = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (operation){
                case 1:
                    handleAddStudent(sc, academicService);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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


    private static void askStudentId(){
        System.out.print("Type the student ID: ");
    }

    private static void askCourseId(){
        System.out.print("Type the course ID: ");
    }

    private static void handleAddStudent(Scanner sc, AcademicService academicService){
        System.out.println("Student data:");
        System.out.print("Full name: ");
        String studentName = sc.nextLine();
        Student newStudent = new Student(studentIdCount, studentName);
        academicService.addStudent(newStudent);
        System.out.println(newStudent);
        System.out.println("Student successfully registered!");
        studentIdCount ++;
    }
}
