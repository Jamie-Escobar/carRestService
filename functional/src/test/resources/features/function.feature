Feature: Test private status endpoint

  Scenario: Check /private/status
    Given client sends a "GET" request to "/private/status" endpoint
    Then response should be "OK" with HttpStatus 200