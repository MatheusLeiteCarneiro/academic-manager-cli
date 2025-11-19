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

    public void addStudent(Student student){
        studentsMap.put(student.getId(), student);
        enrollments.put(student, new HashSet<>());
    }

    public void addCourse(Course course){
        coursesMap.put(course.getId(), course);
    }

    public void enrollStudent(Integer studentId, Integer courseId) throws NonExistentId {
        Student student = studentsMap.get(studentId);
        Course course = coursesMap.get(courseId);
        if(student == null ){
            throw new NonExistentId("This student ID does not exist");
        }
        if(course == null){
            throw new NonExistentId("This course ID does not exist");
        }
        Set<Course> courseSet = enrollments.get(student);
        courseSet.add(course);
    }

}
