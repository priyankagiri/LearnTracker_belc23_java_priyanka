package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private String specialization;
    private int yearsOfExperience;
    
    public Trainer() {
    }

    public Trainer(String specialization, int yearsOfExperience) {
        super();
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Trainer(int id, String firstName, String lastName, String email,
                   String specialization, int yearsOfExperience) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String getDisplayName() {
        return "Trainer: " + super.getDisplayName();
    }
 
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
        
}

