package SMS;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private static final int MIN_COURSE_NAME_LENGTH = 3;

    private String courseName;
    private String courseId;

    public Course(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Name cannot be null or empty.");
        }
        if (courseName.length() <= MIN_COURSE_NAME_LENGTH) {
            throw new IllegalArgumentException("Course Name must be at least " + MIN_COURSE_NAME_LENGTH + " characters long.");
        }
        this.courseName = courseName;
        this.courseId = "C" + idCounter.incrementAndGet();
    }

    // Course Name getter
    public String getCourseName() {
        return courseName;
    }

    // Course Name setter
    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Name cannot be null or empty.");
        }
        if (courseName.length() <= MIN_COURSE_NAME_LENGTH) {
            throw new IllegalArgumentException("Course Name must be at least " + MIN_COURSE_NAME_LENGTH + " characters long.");
        }
        this.courseName = courseName;
    }

    // Course ID Getter
    public String getCourseId() {
        return courseId;
    }


}
