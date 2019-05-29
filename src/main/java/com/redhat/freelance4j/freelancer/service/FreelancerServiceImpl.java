package com.redhat.freelance4j.freelancer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.redhat.freelance4j.freelancer.model.Freelancer;

@Component
public class FreelancerServiceImpl implements FreelancerService {
	@Override
	public List<Freelancer> getFreelancers() {
		//postgresqlからデータとってきて返す
		List<Freelancer> hoge = new ArrayList<Freelancer>();
		return hoge;
	}
	
	@Override
	public Freelancer getFreelancer(int id) {
		//postgresqlからデータとってきて返す
		Freelancer hoge = new Freelancer();
		hoge.setFreelancerId(id);
		return hoge;
	}
}