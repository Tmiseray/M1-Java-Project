package SMS;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Teacher extends Person {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private static final int MAX_COURSES = 3;

    private String teacherId;
    private String subject;
    public ArrayList<Course> courses;

    public Teacher(String name,
                   int age,
                   String email,
                   String subject,
                   ArrayList<Course> courses) {
        super(name, age, email);

        if (courses != null && courses.size() > MAX_COURSES) {
            throw new IllegalArgumentException("A teacher cannot have more than " + MAX_COURSES + " courses.");
        }

        this.teacherId = "T" + idCounter.incrementAndGet();
        this.subject = subject;
        this.courses = (courses == null) ? new ArrayList<>() : new ArrayList<>(courses);

    }

    // Teacher ID getter
    public String getTeacherId(){ return teacherId; }

    // Subject getter
    public String getSubject() {
        return subject;
    }

    // Subject setter
    public void setSubject(String subject) {
        this.subject = subject;
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