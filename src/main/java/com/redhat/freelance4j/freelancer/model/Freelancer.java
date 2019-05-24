package com.redhat.freelance4j.freelancer.model;

import java.io.Serializable;
import java.util.List;

public class Freelancer implements Serializable {

    private static final long serialVersionUID = 6964558044240061049L;

    private int freelancerId;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> skills ;
    

    public Freelancer() {
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<String> getSkills() {
        return skills;
    }
    
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
