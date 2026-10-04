package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    // part of overloaded method to update student details - not added in main menu
    public Student addStudent(String name) {
        return addStudent(name, "", "", "");
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int studentId = IdGenerator.getNextStudentId();

        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidInputException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidInputException("Last name cannot be null or empty");
        }
        if (email == null || email.isEmpty()) {
            throw new InvalidInputException("Email cannot be null or empty");
        }
        if (batch == null || batch.isEmpty()) {
            throw new InvalidInputException("Batch cannot be null or empty");
        }

        Student student = new Student(studentId, firstName, lastName, email, batch, true);
        students.add(student);
        return student;
    }

    // part of overloaded method to update student details - not added in main menu
    public void addStudent(String name, int age) {
        addStudent(name);
    }

    // Remove a student by ID- not
    public void removeStudent(int studentId) {
        students.removeIf(student -> student.getId() == studentId);
    }

    // update student details by id and name
    public void updateStudent(int studentId, String newName) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                student.setFirstName(newName);
                return;
            }
        }

        throw new EntityNotFoundException(
                "Student not found with ID: " + studentId);
    }

    // part of overloaded method to update student details - not added in main menu
    public void updateStudent(int studentId, String newName, int newAge) {
        updateStudent(studentId, newName);
    }

    // view all students
    public void listStudents() {
        for (Student student : students) {
            displayStudentDetails(student);
        }
    }

    // Search student by ID
    public void findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                displayStudentDetails(student);
                return;

            }
        }
    }


    public boolean studentExists(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return true;
            }
        }
        return false;
    }

    // Deactivate a student (set active = false instead of deleting)
    public void deactivateStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                student.setActive(false);
                return;
            }
        }
        throw new EntityNotFoundException(
                "Student not found with ID: " + studentId);
    }

    private void displayStudentDetails(Student student) {
        System.out.println("============================================");
        System.out.println("ID: " + student.getId());
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Active: " + student.isActive());
        System.out.println("============================================");
    }
}