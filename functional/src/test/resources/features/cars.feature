Feature: Adding a car to the database
  Scenario: Adding a car to the database
    Given I have the following car
      | brand | model | year | price | mileage | colour     |
      | BMW   | X5    | 2022 | 80000 | 10000   | space grey |
    Given client sends a "POST" request to "/cars/admin" endpoint
    Then response HttpStatus should be 201
    And response body should contain key "description" and value should be "Database updated"

  Scenario: Getting all cars from the database
    When client sends a "GET" request to "/cars/admin" endpoint
    Then response HttpStatus should be 200
    And client receives JSON containing
      | brand | model | year | price | mileage | colour     |
      | BMW   | X5    | 2022 | 80000 | 10000   | space grey |

  Scenario: Post a car using the "/cars/admin" endpoint which already exists
    Given The following car exists in the database
      | brand | model | year | price | mileage | colour |
      | BMW   | X5    | 2022 | 80000 | 10000   | space grey |
    Given client sends a "POST" request to "/cars/admin" endpoint
    Then response HttpStatus should be 409
    And response body should contain key "description" and value should be "Car already exists"
