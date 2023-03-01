Feature: Delete Take
In order to delete Take
I want to delete a Take

  Scenario: Delete a Take
    Given There is Take available with id 1
    Then The response code is 200
    When I delete Take with id 1
    Then The response code is 202
    And Take with id 1 has been deleted
    And The response code is 200