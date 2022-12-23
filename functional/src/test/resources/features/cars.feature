Feature: test cars/admin

  Scenario: Adding a car to the database
    Given I have the following car
      | brand | model | year | price | mileage | colour     |
      | BMW   | X5    | 2022 | 80000 | 10000   | space grey |
    Given client sends a "POST" request to "/cars/admin" endpoint
    Then response HttpStatus should be 201
    And response body should contain key "description" and value should be "Database updated"

  Scenario: POST a car using the /car/admin endpoint with malformed attribute
    Given I want to add the following car with JSON '[{brand: "BMW","model": "X5","year": 2022, "price": 80000, "mileage": 10000, "colour": "space grey"}]'
    When I POST to the '/cars/admin' endpoint
    Then the client receives status code of 400
    And the JSON should contain the key 'description' with value 'Incorrect car data provided'

  Scenario: POST a car using the /cars/admin endpoint with malformed field
    Given I want to add the following car with JSON '[{"brand": BMW,"model": "X5","year": 2022,"price": 80000,"mileage": 10000,"colour": "space grey"}]'
    When I POST to the '/cars/admin' endpoint
    Then the client receives status code of 400
    And the JSON should contain the key 'description' with value 'Incorrect car data provided'

  Scenario: POST a car using the /cars/admin endpoint with missing attribute
    Given I want to add the following car
      | [blank]| model | price | year | mileage | colour |
      | BMW   | X5    | 80000 | 2022 | 10000   | Space Grey |
    When I POST to the '/cars/admin' endpoint
    Then the client receives status code of 400
    And the JSON should contain the key 'description' with value 'Incorrect car data provided'

  Scenario: POST a car using the /cars/admin endpoint with missing field
    Given  I want to add the following car
      | brand | model | price | year | mileage | colour |
      |[blank]| X5    | 80000 | 2022 | 10000   | Space Grey |
    When I POST to the '/cars/admin' endpoint
    Then the client receives status code of 400
    And the JSON should contain the key 'description' with value 'Incorrect car data provided'

  Scenario: Post a car using the /cars/admin endpoint which already exists
    Given The following car exists in the database
      | brand | model | price | year | mileage | colour |
      | BMW   | X5    | 80000 | 2022 | 10000   | Space Grey |
    When I POST to the '/cars/admin/' endpoint
    Then the client receives status code of 409
    And the JSON should contain the key 'description' with value 'Car already exists'

  Scenario: Getting all cars from the database
    When client sends a "GET" request to "/cars/admin" endpoint
    Then response HttpStatus should be 200
    And client receives JSON containing
      | brand | model | year | price | mileage | colour     |
      | BMW   | X5    | 2022 | 80000 | 10000   | space grey |