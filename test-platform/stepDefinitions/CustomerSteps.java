package stepDefinitions;

import businessLogic.AuthService;
import businessLogic.CifService;
import com.github.tomakehurst.wiremock.WireMockServer;
import common.ContentType;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloadHandling.PayloadLoader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CustomerSteps {

    private static WireMockServer wireMockServer;
    private Response response;

    // ================= WIREMOCK SETUP =================
    @BeforeAll
    public static void setupWireMock() {

        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        wireMockServer.stubFor(post(urlEqualTo("/auth/login"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"token\": \"mock-jwt-token\"}")));

        wireMockServer.stubFor(post(urlEqualTo("/api/cif"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": \"123\", \"status\": \"created\"}")));

        RestAssured.baseURI = "http://localhost:8080";
    }

    @AfterAll
    public static void tearDownWireMock() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    // ================= STEPS =================

    @Given("I generate auth token")
    public void token() {
        AuthService.generateToken();
    }

    @When("I create CIF using JSON")
    public void createJson() {
        String payload = PayloadLoader.load("payloads/create_cif.json");
        response = CifService.createCif(payload, ContentType.JSON);
    }

    @When("I create CIF using XML")
    public void createXml() {
        String payload = PayloadLoader.load("payloads/create_cif.xml");
        response = CifService.createCif(payload, ContentType.XML);
    }

    @Then("the user should be created successfully")
    public void userCreatedSuccessfully() {
        response.then().statusCode(201);
    }
}