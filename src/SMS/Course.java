package SMS;

public class Course {
    private String courseName;
    private int courseId;

    public Course(String courseName, int courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if(courseName.length() <= 3) {
            System.out.println("Course Name must be atleast 3 characters long.");
        } else {
            this.courseName = courseName;
        }
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {

    }

}
