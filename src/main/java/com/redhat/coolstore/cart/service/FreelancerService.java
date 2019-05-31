package com.redhat.coolstore.cart.service;

import java.util.List;

import com.redhat.coolstore.cart.model.Freelancer;

public interface FreelancerService {
    List<Freelancer> getFreelancers();
    Freelancer getFreelancer(String freelancerId);
}
