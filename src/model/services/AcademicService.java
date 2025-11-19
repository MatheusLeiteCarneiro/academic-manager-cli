package model.services;

import model.entities.Course;
import model.entities.Student;
import model.exceptions.BlankNameException;
import model.exceptions.EnrollmentException;
import model.exceptions.NonExistentIdException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AcademicService {
    private static int studentIdCount = 1;
    private static int courseIdCount = 1;
    private final Map<Integer, Student> studentsMap;
    private final Map<Integer, Course> coursesMap;
    private final Map<Student, Set<Course>> enrollments;

    public AcademicService() {
        studentsMap = new HashMap<>();
        coursesMap = new HashMap<>();
        enrollments = new HashMap<>();
    }

    private static void verifyBlankName(String name) throws BlankNameException {
        if (name.isBlank()) {
            throw new BlankNameException("The name can't be blank");
        }
    }

    public Student findAndVerifyStudentId(Integer studentId) throws NonExistentIdException {
        Student student = studentsMap.get(studentId);
        if (student == null) {
            throw new NonExistentIdException("This student ID does not exist");
        }
        return student;
    }

    public Course findAndVerifyCourseId(Integer courseId) throws NonExistentIdException {
        Course course = coursesMap.get(courseId);
        if (course == null) {
            throw new NonExistentIdException("This course ID does not exist");
        }
        return course;
    }

    public void verifyIfStudentIsInCourse(Student student, Course course) throws EnrollmentException {
        Set<Course> courses = enrollments.get(student);
        if (!courses.contains(course)) {
            throw new EnrollmentException("This student is not enrolled to this course");
        }
    }

    public void verifyIfStudentIsNotInCourse(Student student, Course course) throws EnrollmentException {
        Set<Course> courses = enrollments.get(student);
        if (courses.contains(course)) {
            throw new EnrollmentException("This student is already enrolled to this course");
        }
    }

    public Student addStudent(String studentName) throws BlankNameException {
        verifyBlankName(studentName);
        Student student = new Student(studentIdCount, studentName);
        studentIdCount++;
        studentsMap.put(student.getId(), student);
        enrollments.put(student, new HashSet<>());
        return student;
    }

    public Course addCourse(String courseName) throws BlankNameException {
        verifyBlankName(courseName);
        Course course = new Course(courseIdCount, courseName);
        courseIdCount++;
        coursesMap.put(course.getId(), course);
        return course;
    }

    public void enrollStudent(Student student, Course course) {
        Set<Course> courseSet = enrollments.get(student);
        courseSet.add(course);
    }

    public List<Course> getCoursesFromAStudent(Student student) {
        Set<Course> courses = enrollments.get(student);
        return courses.stream().collect(Collectors.toList());
    }

    public List<Student> getStudentsFromACourse(Course course) {
        Set<Student> allStudents = enrollments.keySet();
        Stream<Student> studentsOnCourse = allStudents.stream().filter(student -> enrollments.get(student).contains(course));
        return studentsOnCourse.collect(Collectors.toList());
    }

    public void removeAStudentFromACourse(Student student, Course course) {
        enrollments.get(student).remove(course);
    }

    public void deleteAStudent(Student student) {
        studentsMap.remove(student.getId());
        enrollments.remove(student);
    }

    public void deleteACourse(Course course) {
        coursesMap.remove(course.getId());
        enrollments.keySet().forEach(student -> enrollments.get(student).remove(course));
    }

    public List<Student> getAllStudents() {
        return studentsMap.values().stream().collect(Collectors.toList());

    }

    public List<Course> getAllCourses() {
        return coursesMap.values().stream().collect(Collectors.toList());

    }


}
