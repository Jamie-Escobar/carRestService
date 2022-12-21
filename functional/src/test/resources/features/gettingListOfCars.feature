Feature: test cars/admin

  Scenario: check /cars/admin
    Given client sends a "GET" request to "/cars/admin" endpoint
    Then response HttpStatus should be 200
    And response body should contain list of cars and value should contain HttpStatus 200