Feature: Get Take
  In order to get Take
  I want to get a Take

  Scenario: Get a new Take
    Given There is Take available with id 1
    When I get a new Take with id 1
    Then The response code is 200
