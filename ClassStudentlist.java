import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String studentId; // Mã sinh viên
    private String fullName;  // Tên sinh viên

    // Phương thức nhập thông tin sinh viên
    public void enterStudentInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        studentId = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        fullName = scanner.nextLine();
    }

    // Phương thức hiển thị thông tin sinh viên
    public void displayStudentInfo() {
        System.out.println("ID: " + studentId + ", Name: " + fullName);
    }
}

class StudentList {
    private final ArrayList<Student> studentList = new ArrayList<>();

    // Thêm sinh viên vào danh sách
    public void addStudent() {
        Student student = new Student();
        student.enterStudentInfo();
        studentList.add(student);
        System.out.println("Student added successfully!");
    }

    // Hiển thị toàn bộ sinh viên trong danh sách
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("Student List:");
            for (Student student : studentList) {
                student.displayStudentInfo();
            }
        }
    }
}

// Main Class
public class ClassStudentlist {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student  2. Display All Students  3. Exit");
            System.out.print("Choose an option: ");
            int choice = -1;

            // Xử lý ngoại lệ nếu người dùng nhập không phải số
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            // Xử lý từng lựa chọn
            switch (choice) {
                case 1:
                    studentList.addStudent();
                    break;
                case 2:
                    studentList.displayAllStudents();
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please choose 1, 2, or 3.");
            }
        }
    }
}
