package model.services;

import model.entities.Course;
import model.entities.Student;
import model.exceptions.NonExistentId;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AcademicService {
    private Map<Integer, Student> studentsMap;
    private Map<Integer, Course> coursesMap;
    private Map<Student, Set<Course>> enrollments;

    public AcademicService() {
    }

    public Map<Integer, Student> getStudentsMap() {
        return studentsMap;
    }

    public Map<Integer, Course> getCoursesMap() {
        return coursesMap;
    }


    public Map<Student, Set<Course>> getEnrollments() {
        return enrollments;
    }


}
