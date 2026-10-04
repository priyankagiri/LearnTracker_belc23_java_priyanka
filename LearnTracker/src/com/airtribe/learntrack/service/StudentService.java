package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        validateRequiredText(firstName, "First name");
        validateRequiredText(lastName, "Last name");
        validateRequiredText(email, "Email");
        validateRequiredText(batch, "Batch");

        Student student = new Student(firstName, lastName, email, batch, true);
        students.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        validateRequiredText(firstName, "First name");
        validateRequiredText(lastName, "Last name");
        validateRequiredText(batch, "Batch");

        Student student = new Student(firstName, lastName, batch, true);
        students.add(student);
        return student;
    }

    private void validateRequiredText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be null or empty");
        }
    }

    // Deactivate a student without deleting enrollment references
    public void removeStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                student.setActive(false);
                return;
            }
        }
        throw new EntityNotFoundException(
                "Student not found with ID: " + studentId);
    }

    // Update all student details together to keep the entity consistent.
    public void updateStudent(int studentId, String firstName, String lastName,
            String email, String batch) {
        validateRequiredText(firstName, "First name");
        validateRequiredText(lastName, "Last name");
        validateRequiredText(email, "Email");
        validateRequiredText(batch, "Batch");

        for (Student student : students) {
            if (student.getId() == studentId) {
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);
                student.setBatch(batch);
                return;
            }
        }

        throw new EntityNotFoundException(
                "Student not found with ID: " + studentId);
    }

    public void updateStudent(int studentId, String firstName, String lastName, String batch) {
        validateRequiredText(firstName, "First name");
        validateRequiredText(lastName, "Last name");
        validateRequiredText(batch, "Batch");

        for (Student student : students) {
            if (student.getId() == studentId) {
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail("");
                student.setBatch(batch);
                return;
            }
        }

        throw new EntityNotFoundException(
                "Student not found with ID: " + studentId);
    }

    // view all students
    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    // Search student by ID
    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new EntityNotFoundException(
                "Student not found with ID: " + studentId);
    }


    public boolean studentExists(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId && student.isActive()) {
                return true;
            }
        }
        return false;
    }

    public boolean studentExistsById(int studentId) {
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

}