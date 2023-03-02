Feature: Delete Take
In order to delete Take
I want to delete a Take

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"

  Scenario: Delete a Take with user
    Given I login as "username" with password "password"
    And I create a new Take with amount 5, weight 5 and location "Lleida"
    When I delete Take
    Then The response code is 204
    And Take has been deleted
    And The response code is 404