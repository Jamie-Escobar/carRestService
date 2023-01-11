package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private static Response response;
    private String requestData;
    private static String jsonString;
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

    @Then("response HttpStatus should be {int}")
    public void private_response_status_code(int status) {

        assertEquals(status, response.getStatusCode());
    }

    @Then("response should be {string} with HttpStatus {int}")
    public void private_response_returned(String body, int status) {
        jsonString = response.asString();
        assertEquals(body, jsonString);
        assertEquals(status, response.getStatusCode());
    }

    @Given("I have the following car")
    public void iHaveTheFollowingCar(List<Map<String, String>> dataTable) throws JsonProcessingException {

        requestData = mapper.writeValueAsString(dataTable);
    }

    @And("response body should contain key {string} and value should be {string}")
    public void responseBodyShouldContainKeyAndValueShouldBe(String expectedKey, String expectedVal) throws JsonProcessingException {

        var passedResult = mapper.readValue(response.getBody().asString(), Map.class);
        Assertions.assertTrue(passedResult.containsKey(expectedKey));
        assertEquals(expectedVal, passedResult.get(expectedKey));
    }

    @And("client receives JSON containing")
    public void clientReceivesJSONContaining(List<Map<String, String>> dataTable) throws JsonProcessingException {

        requestData = mapper.writeValueAsString(dataTable);
    }

    @Given("The following car exists in the database")
    public void theFollowingCarExistsInTheDatabase(List<Map<String, String>> dataTable) throws JsonProcessingException {
        requestData = mapper.writeValueAsString(dataTable);
    }

    @Given("I want to add the following car with JSON {string}")
    public void iWantToAddTheFollowingCarWithJSONBrand(String carJSON) {

        requestData = String.valueOf(given().contentType(ContentType.JSON).body(carJSON));
    }

    @When("the client GETs the endpoint {string} with query {string}")
    public void theClientGETsTheEndpointCarsAdminWithQueryModelX(String endpoint, String query) {

        Map<String, String> queryParams = splitQueryParams(query);
        response = given().queryParams(queryParams).get(endpoint);
    }

    private Map<String, String> splitQueryParams(String query) {

        String[] queryParts = query.split("&");
        return Arrays.stream(queryParts)
                .map((queryKeyValue) -> queryKeyValue.split("="))
                .collect(Collectors.toMap(splitString -> splitString[0], splitString -> splitString.length > 1 ? splitString[1] : ""));
    }
}

