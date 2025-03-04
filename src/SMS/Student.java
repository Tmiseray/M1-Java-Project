package SMS;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Student extends Person {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private static final int MAX_COURSES = 5;
    private String studentId;
    private int grade;
    private ArrayList<Course> courses;

    public Student(String name,
                   int age,
                   String email,
                   int grade,
                   ArrayList<Course> courses) {
        super(name, age, email);

        if (name == null || email == null) {
            throw new IllegalArgumentException("Name or email cannot be null.");
        }

        this.studentId = "S" + idCounter.incrementAndGet();
        this.grade = grade;
        this.courses = (courses == null) ? new ArrayList<>() : courses;
    }

    // Student ID Getter
    public String getStudentId() { return studentId; }

    // Grade Getter
    public int getGrade() { return grade; }

    // Grade Setter
    public void setGrade(int grade) {
        if (grade < 0 || grade > 12) {
            System.out.println("Grade must be between 1 and 12.");
        } else {
            this.grade = grade;
        }
    }


    // Course List getter
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Course List Add course
    public boolean addCourse(Course course) {
        if (course == null) {
            System.out.println("Cannot add a null course.");
            return false;
        }
        if (this.courses.contains(course)) {
            System.out.println("This course is already assigned.");
            return false;
        }
        if (this.courses.size() >= MAX_COURSES) {
            System.out.println("Maximum number of courses reached.");
            return false;
        }
        this.courses.add(course);
        return true;
    }



}
