package com.airtribe.learntrack.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private EnrollmentStatus status;

    /**
     * Default constructor
     */
	public Enrollment() {
	}

	/** 
     * Parameterized constructor
     */
    public Enrollment(int studentId, int courseId, String enrollmentDate, EnrollmentStatus status) {
        this(IdGenerator.getNextEnrollmentId(), studentId, courseId, enrollmentDate, status);
    }

	/** 
     * Parameterized constructor
     */
    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        setStudentId(studentId);
        setCourseId(courseId);
        setEnrollmentDate(enrollmentDate);
        this.status = status;
    }


    /**
     * Getters and setters
     */
	public int getId() {
		return id;  
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new InvalidInputException("Student ID must be greater than zero");
        }
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
        if (courseId <= 0) {
            throw new InvalidInputException("Course ID must be greater than zero");
        }
		this.courseId = courseId;
	}
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
        if (enrollmentDate == null || enrollmentDate.trim().isEmpty()) {
            throw new InvalidInputException("Enrollment date cannot be null or empty");
        }
        try {
            this.enrollmentDate = LocalDate.parse(enrollmentDate.trim()).toString();
        } catch (DateTimeParseException exception) {
            throw new InvalidInputException(
                    "Enrollment date must use the format yyyy-MM-dd");
        }
	}
	public EnrollmentStatus getStatus() {
		return status;
	}
	public void setStatus(EnrollmentStatus status) {
		this.status = status;
	}


}
