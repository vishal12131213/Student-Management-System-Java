package dao;

import model.Student;
import util.DBConnection;
import java.sql.*;

public class StudentDAO {

    public void addStudent(Student s) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students(name,email,course) VALUES(?,?,?)");

            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getCourse());

            ps.executeUpdate();
            System.out.println("Student added successfully.");

        } catch (Exception e) {
            System.out.println("Unable to add student. Please try again.");
        }
    }


    public void viewStudents() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement()
                .executeQuery("SELECT * FROM students");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getString("email") + " " +
                            rs.getString("course")
            );
        }
    }
    public void updateStudent(int id, String name, String email, String course) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE students SET name=?, email=?, course=? WHERE id=?");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student updated successfully." : "Student not found.");

        } catch (Exception e) {
            System.out.println("Unable to update student.");
        }
    }

    public void deleteStudent(int id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM students WHERE id=?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted successfully." : "Student not found.");

        } catch (Exception e) {
            System.out.println("Unable to delete student.");
        }
    }

    public void searchByName(String name) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE name LIKE ?");
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("course")
                );
            }

            if (!found) {
                System.out.println("No student found with this name.");
            }

        } catch (Exception e) {
            System.out.println("Search failed.");
        }
    }

    public void searchByEmail(String email) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("course")
                );
            } else {
                System.out.println("No student found with this email.");
            }

        } catch (Exception e) {
            System.out.println("Search failed.");
        }
    }



}
