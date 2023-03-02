Feature: Create Take
In order to create Take
I want to create a new Take

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And I login as "username" with password "password"

  Scenario: Create a new Take
    When I create a new Take with amount 5, weight 5 and location "Lleida"
    Then The response code is 201
    And I can retrieve that Take
    And The response code is 200