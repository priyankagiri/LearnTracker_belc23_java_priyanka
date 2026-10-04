package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class Course {
    private int id;
	private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    /**
     * Parameterized constructor
     */
    public Course(String courseName, String description, int durationInWeeks, boolean active) {
        this(IdGenerator.getNextCourseId(), courseName, description, durationInWeeks, active);
    }

    /**
     * Constructor for restoring a course with an existing ID.
     */
    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        setId(id);
        setCourseName(courseName);
        setDescription(description);
        setDurationInWeeks(durationInWeeks);
        this.active = active;
	}


    /** 
     * Default constructor
     */
    public Course() {
    }

    
    /**
     * Getters and setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InvalidInputException("Course ID must be greater than zero");
        }
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new InvalidInputException("Course name cannot be null or empty");
        }
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidInputException("Course description cannot be null or empty");
        }
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        if (durationInWeeks < 0) {
            throw new InvalidInputException("Course duration cannot be negative");
        }
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}