package com.airtribe.learntrack.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Enroll a student in a course
    public Enrollment enrollStudentInCourse(int studentId, int courseId) {
        if (studentId <= 0 || courseId <= 0) {
            throw new InvalidInputException(
                    "Student ID and course ID must be greater than zero");
        }
        if (!studentService.studentExists(studentId)) {
            throw new InvalidInputException("Student not found with ID: " + studentId);
        }
        if (!courseService.courseExists(courseId)) {
            throw new InvalidInputException("Course not found with ID: " + courseId);
        }
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId
                    && enrollment.getCourseId() == courseId
                    && enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException(
                        "Student is already actively enrolled in this course");
            }
        }

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                studentId,
                courseId,
                LocalDate.now().toString(),
                EnrollmentStatus.ACTIVE);
        enrollments.add(enrollment);
        return enrollment;
    }

    // View enrollments for a student
    public List<Enrollment> viewEnrollmentsForStudent(int studentId) {
        if (studentId <= 0) {
            throw new InvalidInputException("Student ID must be greater than zero");
        }
        if (!studentService.studentExistsById(studentId)) {
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }

        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }

        if (studentEnrollments.isEmpty()) {
            throw new EntityNotFoundException(
                    "No enrollments found for student with ID: " + studentId);
        }
        return studentEnrollments;
    }

    // Mark enrollment as completed/cancelled
    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status) {
        if (enrollmentId <= 0) {
            throw new InvalidInputException("Enrollment ID must be greater than zero");
        }
        if (status == null) {
            throw new InvalidInputException("Status cannot be null or empty");
        }

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                enrollment.setStatus(status);
                return;
            }
        }

        throw new EntityNotFoundException(
                "Enrollment not found with ID: " + enrollmentId);
    }
}
