package model.services;

import model.entities.Course;
import model.entities.Student;
import model.exceptions.NonExistentId;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Student findAndVerifyStudentId(Integer studentId) throws NonExistentId {
        Student student = studentsMap.get(studentId);
        if(student == null ){
            throw new NonExistentId("This student ID does not exist");
        }
        return student;
    }

    public Course findAndVerifyCourseId(Integer courseId) throws NonExistentId {
        Course course = coursesMap.get(courseId);
        if(course == null){
            throw new NonExistentId("This course ID does not exist");
        }
        return course;
    }

    public void addStudent(Student student){
        studentsMap.put(student.getId(), student);
        enrollments.put(student, new HashSet<>());
    }

    public void addCourse(Course course){
        coursesMap.put(course.getId(), course);
    }

    public void enrollStudent(Integer studentId, Integer courseId) throws NonExistentId {
        Student student = findAndVerifyStudentId(studentId);
        Course course = findAndVerifyCourseId(courseId);
        Set<Course> courseSet = enrollments.get(student);
        courseSet.add(course);
    }

    public List<Course> getCoursesFromAStudent(Integer studentId) throws NonExistentId {
        Student student = findAndVerifyStudentId(studentId);
        Set<Course> courses = enrollments.get(student);
        return courses.stream().collect(Collectors.toList());
    }

    public List<Student> getStudentsFromACourse(Integer courseId) throws NonExistentId{
        Course course = findAndVerifyCourseId(courseId);
        Set<Student> allStudents = enrollments.keySet();
        Stream<Student> studentsOnCourse = allStudents.stream().filter(student -> enrollments.get(student).contains(course));
        return studentsOnCourse.collect(Collectors.toList());
    }

    public void removeAStudentFromACourse(Integer studentId,Integer courseId) throws NonExistentId {
        Student student = findAndVerifyStudentId(studentId);
        Course course = findAndVerifyCourseId(courseId);
        enrollments.get(student).remove(course);
    }

    public void deleteAStudent(Integer studentId) throws NonExistentId {
        Student student = findAndVerifyStudentId(studentId);
        studentsMap.remove(studentId);
        enrollments.remove(student);
    }

    public void deleteACourse(Integer courseId) throws NonExistentId{
        Course course = findAndVerifyCourseId(courseId);
        coursesMap.remove(courseId);
        enrollments.keySet().forEach(student -> enrollments.get(student).remove(course));
    }










}
