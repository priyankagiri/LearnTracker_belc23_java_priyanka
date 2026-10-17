package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Student extends Person {
    private String batch;
    private boolean active;

    /**
     * Constructor for students without an email address.
     */
    public Student(String firstName, String lastName, String batch, boolean active) {
        this(firstName, lastName, "", batch, active);
    }

   
    public Student(String firstName, String lastName, String email, String batch, boolean active) {
        this(IdGenerator.getNextStudentId(), firstName, lastName, email, batch, active);
    }

    /**
     * Constructor for restoring a student with an existing ID.
     */
    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return "Student: " + super.getDisplayName();
    }

    /**
     * Getters and setters
     */
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
