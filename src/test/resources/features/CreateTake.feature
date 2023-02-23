Feature: Create Take
In order to create Take
I want to create a new Take

  Scenario: Create a new Take
    Given There is no Take available with id 1
    When I create a new Take 
    Then The response code is 201
    And Take has been created with id 1