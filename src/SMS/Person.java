package SMS;

public class Person {
    private String name;
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
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
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("\nAge cannot be negative");
        }
    }

    // Email Getter
    public String getEmail() {
        return email;
    }

    // Email Setter
    public void setEmail(String email) {
        if (!email.matches(
                "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b"
        )) {
            System.out.println("Must enter a valid email address. EX: example@example.com");
        } else {
            this.email = email;
        }
    }
}
