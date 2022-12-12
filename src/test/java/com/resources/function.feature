Feature: Test private status endpoint

  Scenario: Check /private/status
    Given  localhost:8080/private/status
    When private response returned
    And press enter
    Then response should be "OK" with HttpStatus 200