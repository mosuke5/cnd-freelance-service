package com.redhat.freelance4j.freelancer.service;

import com.redhat.freelance4j.freelancer.model.Freelancer;

public interface FreelancerService {
    public Freelancer getFreelancers();
    public Freelancer getFreelancer(int freelancerId);
}
