import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    @Given("localhost:{8080}\\/private\\/status")
    public void localhost_private_status(Integer int1) {

        throw new io.cucumber.java.PendingException();
    }

    @When("private response returned")
    public void private_response_returned() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("press enter")
    public void press_enter() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("response should be {string} with HttpStatus {int}")
    public void response_should_be_with_http_status(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
