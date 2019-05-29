package com.redhat.coolstore.cart.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.util.ReflectionTestUtils;

import com.redhat.freelance4j.freelancer.model.Freelancer;
import com.redhat.freelance4j.freelancer.service.FreelancerService;
import com.redhat.freelance4j.freelancer.service.FreelancerServiceImpl;

public class FreelancerServiceImplTest {

    private FreelancerService freelancerService;

    @Before
    public void setup() {
        freelancerService = new FreelancerServiceImpl();
    }

    @Test
    public void testGetFreelancer() {
        Freelancer f = freelancerService.getFreelancer(1);

        assertThat(f, notNullValue());
        assertThat(f.getFreelancerId(), equalTo(1));
    }

}
