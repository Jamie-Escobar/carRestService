package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class StepDefinitions {
    private static Response response;
    private static String jsonString;

    @Given("client sends a request to {string} endpoint")
    public void localhost_private_status(String endpoint) {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        response = request.get(endpoint);
    }

    @Then("response should be {string} with HttpStatus {int}")
    public void private_response_returned(String body, int status) {
        jsonString = response.asString();
        Assertions.assertEquals(body, jsonString);
        Assertions.assertEquals(status, response.getStatusCode());
    }

}
