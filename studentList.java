import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String id, name;

    public Student() {}

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void enterInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID: ");
        id = sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

class StudentList {
    private final ArrayList<Student> students = new ArrayList<>();

    public void addStudents(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Enter info for student " + (i + 1) + ":");
            Student s = new Student();
            s.enterInfo();
            students.add(s);
        }
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("Student List:");
        students.forEach(Student::displayInfo);
    }

    public Student findById(String id) {
        return students.stream().filter(s -> s.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public boolean deleteById(String id) {
        Student s = findById(id);
        return s != null && students.remove(s);
    }

    public boolean editById(String id) {
        Student s = findById(id);
        if (s != null) {
            System.out.println("Editing student with ID: " + id);
            s.enterInfo();
            return true;
        }
        return false;
    }
}

public class Processor {
    public static void main(String[] args) {
        StudentList list = new StudentList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Students  2. Display All  3. Find by ID");
            System.out.println("4. Delete by ID  5. Edit by ID  6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter number of students: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    list.addStudents(n);
                }
                case 2 -> list.displayAll();
                case 3 -> {
                    System.out.print("Enter ID to find: ");
                    String id = sc.nextLine();
                    Student s = list.findById(id);
                    if (s != null) s.displayInfo();
                    else System.out.println("Student not found.");
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    String id = sc.nextLine();
                    if (list.deleteById(id)) System.out.println("Deleted successfully.");
                    else System.out.println("Student not found.");
                }
                case 5 -> {
                    System.out.print("Enter ID to edit: ");
                    String id = sc.nextLine();
                    if (list.editById(id)) System.out.println("Edited successfully.");
                    else System.out.println("Student not found.");
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
