Feature: Create Take
In order to create Take
I want to create a new Take

  Scenario: Create a new Take
    When I create a new Take
    Then The response code is 201
    And I can retrieve that Take
    And The response code is 200