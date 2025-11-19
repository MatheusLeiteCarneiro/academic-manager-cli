package application;

import model.services.AcademicService;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        AcademicService academicService = new AcademicService();
        System.out.println("Welcome to the academic manager system!\n");

        sc.close();
    }
}
