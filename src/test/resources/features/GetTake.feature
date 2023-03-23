Feature: Get Take
  In order to get Take
  I want to get a Take


  Background:
    Given There is a registered user with username "user" and password "password" and email "user@user.com"
    Given There is a registered admin with username "admin" and password "password" and email "admin@user.com"
    Given There is a registered donor with username "donor" and password "password" and email "donor@user.com"
    Given There is a registered propagator with username "propagator" and password "password" and email "propagator@sample.app" with the following takes
      | amount  |  weight   | location  | date                      |
      | 1       |    2      | Lleida    |2023-03-09T14:30:00+01:00  |

  Scenario: Get a new Take as user
    Given I login as "propagator" with password "password"
    When I try to retrieve that Take
    Then The response code is 200

  Scenario: Get a new Take as admin
    Given I login as "propagator" with password "password"
    When I try to retrieve that Take
    Then The response code is 200

  Scenario: Get a new Take as donor
    Given I login as "propagator" with password "password"
    When I try to retrieve that Take
    Then The response code is 200

  Scenario: Get a new Take as propagator
    Given I login as "propagator" with password "password"
    When I try to retrieve that Take
    Then The response code is 200
