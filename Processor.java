import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String studentId, fullName;

    void enterInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID: ");
        studentId = sc.nextLine();
        System.out.print("Enter Name: ");
        fullName = sc.nextLine();
    }

    void displayInfo() {
        System.out.println("ID: " + studentId + ", Name: " + fullName);
    }
}

class StudentList {
    ArrayList<Student> students = new ArrayList<>();

    void addStudents(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Enter info for student " + (i + 1) + ":");
            Student s = new Student();
            s.enterInfo();
            students.add(s);
        }
    }

    void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        students.forEach(Student::displayInfo);
    }

    Student findById(String id) {
        return students.stream().filter(s -> s.studentId.equals(id)).findFirst().orElse(null);
    }

    boolean deleteById(String id) {
        return students.removeIf(s -> s.studentId.equals(id));
    }

    boolean editById(String id) {
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
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();

        while (true) {
            System.out.println("\n1. Add Students\n2. Display All\n3. Find by ID\n4. Delete by ID\n5. Edit by ID\n6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> {
                    System.out.print("Number of students to add: ");
                    list.addStudents(sc.nextInt());
                }
                case 2 -> list.displayAll();
                case 3 -> {
                    System.out.print("Enter ID to find: ");
                    Student s = list.findById(sc.nextLine());
                    if (s != null) s.displayInfo();
                    else System.out.println("Not found.");
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    System.out.println(list.deleteById(sc.nextLine()) ? "Deleted." : "Not found.");
                }
                case 5 -> {
                    System.out.print("Enter ID to edit: ");
                    System.out.println(list.editById(sc.nextLine()) ? "Edited." : "Not found.");
                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
