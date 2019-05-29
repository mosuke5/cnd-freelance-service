package com.redhat.coolstore.cart.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.redhat.coolstore.cart.service.CatalogService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreelancerEndpointTest {

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Autowired
    private CatalogService catalogService;

    @Before
    public void beforeTest() throws Exception {
        RestAssured.baseURI = String.format("http://localhost:%d/freelancer", port);
        ReflectionTestUtils.setField(catalogService, null, "catalogServiceUrl", "http://localhost:" + wireMockRule.port(), null);
        initWireMockServer();
    }

    @Test
    public void retrieveCartById() throws Exception {
        given().get("/{cartId}", "123456")
            .then()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo("123456"))
            .body("cartItemTotal", equalTo(0.0f))
            .body("shoppingCartItemList", hasSize(0));
    }
    
    private void initWireMockServer() throws Exception {
        InputStream isresp = Thread.currentThread().getContextClassLoader().getResourceAsStream("catalog-response.json");

        stubFor(get(urlEqualTo("/product/111111")).willReturn(
                aResponse().withStatus(200).withHeader("Content-type", "application/json").withBody(IOUtils.toString(isresp, Charset.defaultCharset()))));

        stubFor(get(urlEqualTo("/product/error")).willReturn(
                aResponse().withStatus(500)));
    }
    
}
