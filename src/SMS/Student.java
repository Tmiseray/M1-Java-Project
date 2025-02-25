package SMS;

import java.util.ArrayList;

public class Student extends Person {
    private int studentId;
    private int grade;
    private ArrayList<Course> courses;

    public Student(String name,
                   int age,
                   String email,
                   int studentId,
                   int grade,
                   ArrayList<Course> courses) {
        super(name, age, email);
        this.studentId = studentId;
        this.grade = grade;
        this.courses = courses;
    }

    // Name Getter
    @Override
    public String getName() {
        return super.getName();
    }
}
