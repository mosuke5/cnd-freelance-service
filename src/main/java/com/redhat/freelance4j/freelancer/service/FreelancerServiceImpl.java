package com.redhat.freelance4j.freelancer.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redhat.freelance4j.freelancer.model.Freelancer;

@Component
public class FreelancerServiceImpl implements FreelancerService {
	//@PersistenceContext
	@Autowired
	private EntityManager em;

	@Override
	public List<Freelancer> getFreelancers() {
		//postgresqlからデータとってきて返す
		List<Freelancer> hoge = new ArrayList<Freelancer>();
		Freelancer f1 = new Freelancer();
		f1.setFreelancerId(1);
		Freelancer f2 = new Freelancer();
		f1.setFreelancerId(2);
		hoge.add(f1);
		hoge.add(f2);
		return hoge;
	}
	
	@Override
	public Freelancer getFreelancer(int id) {
		//postgresqlからデータとってきて返す
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
		Freelancer hoge = em.find(Freelancer.class, id);
		System.out.println(hoge.getFreelancerId());

		//Freelancer hoge = new Freelancer();
		//hoge.setFreelancerId(id);
		return hoge;
	}
}