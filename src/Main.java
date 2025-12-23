import dao.StudentDAO;
import model.Student;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();


        while (true) {   // 🔁 MENU LOOP
            System.out.println("\n====== Student Management System ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("6. Search by Name");
            System.out.println("7. Search by Email");
            System.out.println("8. Count Total Students");
            System.out.println("9. Search by Course");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        if (name.trim().isEmpty()) {
                            System.out.println("Name cannot be empty.");
                            break;
                        }


                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        if (!email.contains("@") || !email.contains(".")) {
                            System.out.println("Invalid email format.");
                            break;
                        }


                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        if (course.trim().isEmpty()) {
                            System.out.println("Course cannot be empty.");
                            break;
                        }


                        dao.addStudent(new Student(name, email, course));
                        break;

                    case 2:
                        dao.viewStudents();
                        break;

                    case 3:
                        System.out.print("Enter Student ID to update: ");
                        int uid = sc.nextInt();

                        if (uid <= 0) {
                            System.out.println("Invalid ID.");
                            break;
                        }

                        sc.nextLine();

                        System.out.print("New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("New Email: ");
                        String newEmail = sc.nextLine();

                        System.out.print("New Course: ");
                        String newCourse = sc.nextLine();

                        dao.updateStudent(uid, newName, newEmail, newCourse);
                        break;

                    case 4:
                        System.out.print("Enter Student ID to delete: ");
                        int did = sc.nextInt();

                        if (did <= 0) {
                            System.out.println("Invalid ID.");
                            break;
                        }


                        dao.deleteStudent(did);
                        break;

                    case 5:
                        System.out.println("Exiting application. Goodbye!");
                        System.exit(0);

                    case 6:
                        sc.nextLine();
                        System.out.print("Enter name to search: ");
                        String searchName = sc.nextLine();

                        if (searchName.trim().isEmpty()) {
                            System.out.println("Name cannot be empty.");
                            break;
                        }

                        dao.searchByName(searchName);
                        break;
                    case 7:
                        sc.nextLine();
                        System.out.print("Enter email to search: ");
                        String searchEmail = sc.nextLine();

                        if (!searchEmail.contains("@")) {
                            System.out.println("Invalid email.");
                            break;
                        }

                        dao.searchByEmail(searchEmail);
                        break;
                    case 8:
                        dao.countStudents();
                        break;
                    case 9:
                        sc.nextLine();
                        System.out.print("Enter course name to search: ");
                        String courseSearch = sc.nextLine();

                        if (courseSearch.trim().isEmpty()) {
                            System.out.println("Course cannot be empty.");
                            break;
                        }

                        dao.searchByCourse(courseSearch);
                        break;
                    default:
                    System.out.println("Invalid choice. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
