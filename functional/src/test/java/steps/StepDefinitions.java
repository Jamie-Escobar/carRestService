package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepDefinitions {
    private static Response response;
    private static String jsonString;
    private String requestData;
    private ObjectMapper mapper = new ObjectMapper();

    @Given("client sends a {string} request to {string} endpoint")
    public void localhost_private_status(String requestType, String endpoint) {
        RequestSpecification request = given();
        request.contentType(ContentType.JSON);
        switch (requestType) {
            case "GET":
                response = request.get(endpoint);
                break;
            case "POST":
                response = request.body(requestData).post(endpoint);
                break;
            default:
                throw new RuntimeException(requestType + " is not a valid request");
        }

    }

    @Given("I have the following car")
    public void i_have_the_following_car(List<Map<String, String>> dataTable) throws JsonProcessingException {

        requestData = mapper.writeValueAsString(dataTable);
    }


    @Then("response should be {string} with HttpStatus {int}")
    public void private_response_returned(String body, int status) {
        jsonString = response.asString();
        Assertions.assertEquals(body, jsonString);
        Assertions.assertEquals(status, response.getStatusCode());
    }

    @Then("response HttpStatus should be {int}")
    public void private_response_status_code(int status) {

        Assertions.assertEquals(status, response.getStatusCode());
    }


    @And("response body should contain key {string} and value should be {string}")
    public void responseBodyShouldContainKeyAndValueShouldBe(String expectedKey, String expectedVal) throws JsonProcessingException {

        var passedResult = mapper.readValue(response.getBody().asString(), Map.class);
        Assertions.assertTrue(passedResult.containsKey(expectedKey));
        Assertions.assertEquals(expectedVal, passedResult.get(expectedKey));
    }

    @And("response body should contain list of cars and value should contain HttpStatus {int}")
    public void responseBodyShouldContainListOfCarsAndValueShouldContainHttpStatus(int arg0) {


    }
}
