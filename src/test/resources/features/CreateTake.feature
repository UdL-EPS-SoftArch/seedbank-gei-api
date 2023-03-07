Feature: Create Take
In order to create Take
I want to create a new Take

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"

  Scenario: Create a new Take
    Given I login as "username" with password "password"
    When I create a new Take with amount 5, weight 5 and location "Lleida"
    Then The response code is 201
    And I retrieve that Take
    And The response code is 200
    
  Scenario: Create a new Take When Im not authenticated
    Given I'm not logged in
    When I create a new Take with amount 5, weight 5 and location "Lleida"
    Then The response code is 401

  Scenario: Create a new Take with empty body
    Given I login as "username" with password "password"
    When I create a new Take with empty body
    Then The response code is 500