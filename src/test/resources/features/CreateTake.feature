Feature: Create Take
In order to create Take
I want to create a new Take

  Scenario: Create a new Take
    Given There is no Take available with id 1
    Then The response code is 404
    When I create a new Take with id 1
    Then The response code is 201
    And Take has been created with id 1
    And The response code is 200