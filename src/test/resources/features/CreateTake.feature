Feature: Create Take
In order to create Take
I want to create a new Take

  Background:
    Given There is a registered donor with username "donor" and password "password" and email "user@user.com"
    Given There is a registered propagator with username "propagator" and password "password" and email "propagator@sample.app" with the following takes
      | amount  |  weight   | location  | date                      |
      | 1       |    2      | Lleida    |2023-03-09T14:30:00+01:00  |

  Scenario: Create a new Take as propagator
    Given I login as "propagator" with password "password"
    When I create a new Take with amount 5, weight 5 and location "Lleida"
    Then The response code is 201
    And I try to retrieve that Take
    And The response code is 200
    
  Scenario: Create a new Take When Im not authenticated
    Given I'm not logged in
    When I create a new Take with amount 5, weight 5 and location "Lleida"
    Then The response code is 401

  Scenario: Create a new Take with empty body
    Given I login as "propagator" with password "password"
    When I create a new Take with empty body
    Then The response code is 400

  Scenario: Create a new Take as donor
    Given I login as "donor" with password "password"
    When I create a new Take with amount 5, weight 5 and location "Lleida"
    Then The response code is 403