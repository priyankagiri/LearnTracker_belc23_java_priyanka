package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;

public class Trainer extends Person {
    private String specialization;
    private int yearsOfExperience;
    
    public Trainer(int id, String firstName, String lastName, String email,
                   String specialization, int yearsOfExperience) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
        setYearsOfExperience(yearsOfExperience);
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
        if (yearsOfExperience < 0) {
            throw new InvalidInputException("Years of experience cannot be negative");
        }
        this.yearsOfExperience = yearsOfExperience;
    }
        
}
