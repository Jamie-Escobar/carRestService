Feature: test cars/admin

  Scenario: add a car to database
    Given I have the following car
    |brand|model|year|price|mileage|colour|
    |BMW  |X5   |2022|80000|10000  |space grey|
    Given client sends a "POST" request to "/cars/admin" endpoint
    Then response HttpStatus should be 201
    And response body should contain key "description" and value should be "Database updated"

