package com.redhat.coolstore.cart.rest;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartEndpointTest {

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Before
    public void beforeTest() throws Exception {
        RestAssured.baseURI = String.format("http://localhost:%d/freelancers", port);
    }

    @Test
    public void retrieveFreelancerById() throws Exception {
    	String[] skills = {"ruby", "php", "mysql"};
        given().get("/{freelancerId}", "1")
            .then()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("freelancerId", equalTo("1"));
    }
    
    @Test
    public void retrieveFreelancers() throws Exception {
        given().get("/")
            .then(	)
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

}
