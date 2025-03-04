package SMS;

public class Person {
    private String name;
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        validateAge(age);
        validateEmail(email);

        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Age Validation
    public void validateAge(int age) {
        if (age < 5) {
            throw new IllegalArgumentException("Age cannot be less than 5.");
        }
    }

    // Email Validation
    public void validateEmail(String email) {
        if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")) {
            throw new IllegalArgumentException("Must enter a valid email address. EX: example@example.com");
        }

    }

    // Name Getter
    public String getName() {
        return name;
    }

    // Name Setter
    public void setName(String name) {
        this.name = name;
    }

    // Age Getter
    public int getAge() {
        return age;
    }

    // Age Setter
    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    // Email Getter
    public String getEmail() {
        return email;
    }

    // Email Setter
    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }
}
