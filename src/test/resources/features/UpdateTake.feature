Feature: Update Take
In order to update Take
I want to update a new Take

  Scenario: Update a existent Take
    Given There is a Take available with id 1
    Then The response code is 200
    When I update the Take with id 1
    Then The response code is 201
    And The Take with id 1  has been updated
    Then The response code is 200