package com.airtribe.learntrack.entity;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private Enum<EnrollmentStatus> status;

    /**
     * Default constructor
     */
	public Enrollment() {
	}

	/** 
     * Parameterized constructor
     */
    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, Enum<EnrollmentStatus> status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
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
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public Enum<EnrollmentStatus> getStatus() {
		return status;
	}
	public void setStatus(Enum<EnrollmentStatus> status) {
		this.status = status;
	}


}
