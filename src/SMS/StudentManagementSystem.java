package SMS;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentManagementSystem {
    private static final Scanner GLOBAL_SCANNER = new Scanner(System.in);

    private static final String[] MENU_OPTIONS = {
            "Add Student",
            "Add Teacher",
            "Add Course",
            "Assign Courses to Students",
            "Assign Courses to Teachers",
            "View Student by ID",
            "View All Students",
            "View All Students & Teachers",
            "View All Courses",
            "Update Student",
            "Delete Student",
            "Update Teacher",
            "Delete Teacher",
            "Exit"
    };


    public ArrayList<Student> students;
    public ArrayList<Teacher> teachers;
    public ArrayList<Course> courses;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public int menu() {
        int choice = -1;
        while (choice < 1 || choice > MENU_OPTIONS.length) {
            try {
                System.out.println("\nWelcome to Student Management System\n");
                for (int i = 0; i < MENU_OPTIONS.length; i++) {
                    System.out.println((i + 1) + ". " + MENU_OPTIONS[i]);
                }
                System.out.print("\nEnter your choice: ");
                choice = Integer.parseInt(GLOBAL_SCANNER.nextLine());
                if (choice < 1 || choice > MENU_OPTIONS.length) {
                    System.out.println("Invalid choice. Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }



    // Student Methods
    //
    // Add Student Info
    public void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = GLOBAL_SCANNER.nextLine();
        System.out.print("Enter Student Age: ");
        int age = GLOBAL_SCANNER.nextInt();
        GLOBAL_SCANNER.nextLine();
        System.out.print("Enter Student Email: ");
        String email = GLOBAL_SCANNER.nextLine();
        System.out.print("Enter Student Grade: ");
        int grade = GLOBAL_SCANNER.nextInt();
        GLOBAL_SCANNER.nextLine();
        ArrayList<Course> courseList = selectCourses();

        Student student = new Student(name, age, email, grade, courseList);
        students.add(student);
        System.out.println("\nStudent Added Successfully!");
        displayStudentInfo(student);
    }

    // View Student by ID
    public void studentById() {
        System.out.print("Enter Student ID: ");
        String studentId = GLOBAL_SCANNER.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                displayStudentInfo(student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student does not exist with ID: " + studentId);
        }
    }


    // Display Student Info
    public void displayStudentInfo(Student student) {
        System.out.println("\nStudent ID: " + student.getStudentId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student Age: " + student.getAge());
        System.out.println("Student Email: " + student.getEmail());
        System.out.println("Student Grade: " + student.getGrade());
        System.out.println("Student Courses: ");
        if (student.getCourses().isEmpty()) {
            System.out.println("No Courses Assigned");
        } else {
            for(Course course : student.getCourses()) {
                System.out.println("~ " + course.getCourseName());
            }
        }
    }

    // Assign Courses to Student
    public void assignCoursesToStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = GLOBAL_SCANNER.nextLine();
        boolean studentFound = false;

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                studentFound = true;

                ArrayList<Course> courseList = selectCourses();
                for (Course course : courseList) {
                    if (student.addCourse(course)) {
                        System.out.println(course.getCourseName() + " assigned to " + student.getName());
                    } else {
                        System.out.println(course.getCourseName() + " could not be assigned. It may already be assigned.");
                    }
                }
                break;
            }
        }

        if (!studentFound) {
            System.out.println("Student with ID '" + studentId + "' not found.");
        }
    }


    // Update Student Info
    public void updateStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = GLOBAL_SCANNER.nextLine();
        boolean studentFound = false;

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                studentFound = true;

                System.out.print("Enter Student Name: ");
                String name = GLOBAL_SCANNER.nextLine();

                int age = -1;
                System.out.print("Enter Student Age: ");
                try {
                    age = GLOBAL_SCANNER.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input for age. Please enter a valid integer.");
                    GLOBAL_SCANNER.nextLine();
                    return;
                }
                GLOBAL_SCANNER.nextLine();

                System.out.print("Enter Student Email: ");
                String email = GLOBAL_SCANNER.nextLine();

                int grade = -1;
                System.out.print("Enter Student Grade: ");
                try {
                    grade = GLOBAL_SCANNER.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input for grade. Please enter a valid integer.");
                    GLOBAL_SCANNER.nextLine();
                    return;
                }
                GLOBAL_SCANNER.nextLine();

                // Attempt to Set Student Details
                try {
                    student.setName(name);
                    student.setAge(age);
                    student.setEmail(email);
                    student.setGrade(grade);

                    System.out.println("\nStudent Updated Successfully!");
                    displayStudentInfo(student);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong while updating the student.");
                }
                break;
            }
        }

        if (!studentFound) {
            System.out.println("Student with ID '" + studentId + "' not found.");
        }
    }

    // Delete Student
    public void deleteStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = GLOBAL_SCANNER.nextLine();
        boolean studentFound = false;

        for(Student student : students) {
            if(student.getStudentId().equalsIgnoreCase(studentId)) {
                studentFound = true;
                students.remove(student);
                System.out.println("\nStudent Deleted Successfully!");
                break;
            }
        }
        if(!studentFound) {
            System.out.println("Student with ID '" + studentId + "' not found.");
        }
    }


    // Teacher Methods
    //
    // Add Teacher Info
    public void addTeacher() {
        System.out.print("Enter Teacher Name: ");
        String name = GLOBAL_SCANNER.nextLine();
        System.out.print("Enter Teacher Age: ");
        int age = GLOBAL_SCANNER.nextInt();
        GLOBAL_SCANNER.nextLine();
        System.out.print("Enter Teacher Email: ");
        String email = GLOBAL_SCANNER.nextLine();
        System.out.print("Enter Teacher Subject: ");
        String subject = GLOBAL_SCANNER.nextLine();
        ArrayList<Course> courseList = selectCourses();

        Teacher teacher = new Teacher(name, age, email, subject, courseList);
        teachers.add(teacher);
        System.out.println("\nTeacher Added Successfully!");
        displayTeacherInfo(teacher);
    }

    // Display Teacher Info
    public void displayTeacherInfo(Teacher teacher) {
        System.out.println("\nTeacher ID: " + teacher.getTeacherId());
        System.out.println("Teacher Name: " + teacher.getName());
        System.out.println("Teacher Age: " + teacher.getAge());
        System.out.println("Teacher Email: " + teacher.getEmail());
        System.out.println("Subject: " + teacher.getSubject());
        System.out.println("Teacher Courses: ");
        if (teacher.getCourses().isEmpty()) {
            System.out.println("No Courses Assigned");
        } else {
            for(Course course : teacher.getCourses()) {
                System.out.println("~ " + course.getCourseName());
            }
        }
    }

    // Assign Courses to Teacher
    public void assignCoursesToTeacher() {
        System.out.print("Enter Teacher ID: ");
        String teacherId = GLOBAL_SCANNER.nextLine();
        boolean teacherFound = false;

        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equalsIgnoreCase(teacherId)) {
                teacherFound = true;

                ArrayList<Course> courseList = selectCourses();
                for (Course course : courseList) {
                    if (teacher.addCourse(course)) {
                        System.out.println(course.getCourseName() + " assigned to " + teacher.getName());
                    } else {
                        System.out.println(course.getCourseName() + " could not be assigned. It may already be assigned.");
                    }
                }
                break;
            }
        }

        if (!teacherFound) {
            System.out.println("Teacher with ID '" + teacherId + "' not found.");
        }
    }


    // Update Teacher Info
    public void updateTeacher() {
        System.out.print("Enter Teacher ID: ");
        String teacherId = GLOBAL_SCANNER.nextLine();
        boolean teacherFound = false;

        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equalsIgnoreCase(teacherId)) {
                teacherFound = true;
                System.out.print("Enter Teacher Name: ");
                String name = GLOBAL_SCANNER.nextLine();

                System.out.print("Enter Teacher Age: ");
                int age = -1;
                try {
                    age = GLOBAL_SCANNER.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input for age. Please enter a valid integer.");
                    GLOBAL_SCANNER.nextLine(); // Clearing invalid token
                    return; // Exit method early
                }
                GLOBAL_SCANNER.nextLine(); // Clear buffer

                System.out.print("Enter Teacher Email: ");
                String email = GLOBAL_SCANNER.nextLine();

                System.out.print("Enter Teacher Subject: ");
                String subject = GLOBAL_SCANNER.nextLine();

                try {
                    teacher.setName(name);
                    teacher.setAge(age);
                    teacher.setEmail(email);
                    teacher.setSubject(subject);
                    System.out.println("\nTeacher Updated Successfully!");
                    displayTeacherInfo(teacher);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong!");
                }
                break;
            }
        }
        if (!teacherFound) {
            System.out.println("Teacher with ID '" + teacherId + "' not found.");
        }
    }

    // Delete Teacher
    public void deleteTeacher() {
        System.out.print("Enter Teacher ID: ");
        String teacherId = GLOBAL_SCANNER.nextLine();
        boolean teacherFound = false;

        for(Teacher teacher : teachers) {
            if(teacher.getTeacherId().equalsIgnoreCase(teacherId)) {
                teacherFound = true;
                teachers.remove(teacher);
                System.out.println("\nTeacher Deleted Successfully!");
                break;
            }
        }
        if(!teacherFound) {
            System.out.println("Teacher with ID '" + teacherId + "' not found.");
        }
    }


    // Course Methods
    //
    // Parsing for Courses
    private ArrayList<Course> parseCourses(String coursesInput) {
        ArrayList<Course> validCourses = new ArrayList<>();
        String[] courseArray = coursesInput.split(", ");

        for (String courseName : courseArray) {
            Course eCourse = findCourseByName(courseName);
            if (eCourse != null) {
                validCourses.add(eCourse);
            } else {
                System.out.println("Course does not exist: " + courseName);
            }
        }
        return validCourses;
    }

//    // Check for Existing Course
//    private boolean courseExists(String courseName) {
//        return findCourseByName(courseName) != null;
//    }

    // Return Course Object
    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    // Course Selection
    private ArrayList<Course> selectCourses() {
        System.out.println("Enter Courses Separated by Comma & Space: ");
        String coursesInput = GLOBAL_SCANNER.nextLine().trim();
        return parseCourses(coursesInput);
    }

    // Add Course Info
    public void addCourse() {
        System.out.print("Enter Course Name: ");
        String courseName = GLOBAL_SCANNER.nextLine();

        // Check if the course already exists
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                System.out.println("Course already exists with the name: " + courseName);
                return;
            }
        }

        // Add the course
        Course newCourse = new Course(courseName);
        courses.add(newCourse);
        System.out.println("\nCourse added successfully!");
        displayCourseInfo(newCourse);
    }

    // Display Course Info
    public void displayCourseInfo(Course course) {
        System.out.println("\nCourse ID: " + course.getCourseId());
        System.out.println("Course Name: " + course.getCourseName());
    }


    // Display All Methods
    //
    // Display All Students & Teachers
    public void displayAllStudentsTeachers() {
        System.out.println("\nAll Students & Teachers:");
        displayAllStudents();
        displayAllTeachers();
    }

    // Display All Students
    public void displayAllStudents() {
        System.out.println("\nStudents:");
        if(students.isEmpty()) {
            System.out.println("No Students Added");
        } else {
            for(Student student : students) {
                displayStudentInfo(student);
            }
        }
    }

    // Display All Teachers
    public void displayAllTeachers() {
        System.out.println("\nTeachers:");
        if(teachers.isEmpty()) {
            System.out.println("No Teachers Added");
        } else {
            for(Teacher teacher : teachers) {
                displayTeacherInfo(teacher);
            }
        }
    }

    // Display All Courses
    public void displayAllCourses() {
        System.out.println("\nAll Courses:");
        if(courses.isEmpty()) {
            System.out.println("No Courses Added");
        } else {
            for(Course course : courses) {
                displayCourseInfo(course);
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;
        do {
            choice = sms.menu();

            try {
                switch (choice) {
                    case 1:
                        sms.addStudent();
                        break;
                    case 2:
                        sms.addTeacher();
                        break;
                    case 3:
                        sms.addCourse();
                        break;
                    case 4:
                        sms.assignCoursesToStudent();
                        break;
                    case 5:
                        sms.assignCoursesToTeacher();
                        break;
                    case 6:
                        sms.studentById();
                        break;
                    case 7:
                        sms.displayAllStudents();
                        break;
                    case 8:
                        sms.displayAllStudentsTeachers();
                        break;
                    case 9:
                        sms.displayAllCourses();
                        break;
                    case 10:
                        sms.updateStudent();
                        break;
                    case 11:
                        sms.deleteStudent();
                        break;
                    case 12:
                        sms.updateTeacher();
                        break;
                    case 13:
                        sms.deleteTeacher();
                        break;
                    case 14:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        } while (choice != 14);
        GLOBAL_SCANNER.close();
        System.out.println("\nThank you for using the Student Management System!");
        System.out.println("    . . .~*~^~*` Goodbye! `*~^~*~. . .  ");
        System.out.println("---------------------------------------");
        System.out.println("\tCreated by: T. Miseray");
        System.out.println("---------------------------------------");
        System.out.println("Github: https://github.com/Tmiseray");
        System.out.println("LinkedIn: https://www.linkedin.com/in/taylor-miseray/");
        System.out.println("---------------------------------------");
    }
}


















