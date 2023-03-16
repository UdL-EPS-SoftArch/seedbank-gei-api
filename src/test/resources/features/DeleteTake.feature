Feature: Delete Take
In order to delete Take
I want to delete a Take

  Background:
    Given There is a registered propagator with username "username" and password "password" and email "propagator@sample.app" with the following takes
    | amount  |  weight   | location  | date                      |
    | 1       |    2      | Lleida    |2023-03-09T14:30:00+01:00  |

  Scenario: Delete a Take with user logged in
    Given I login as "username" with password "password"
    When I try to delete Take
    Then The response code is 204
    And I try to retrieve that Take
    And The response code is 404

  Scenario: Delete a Take with user not logged in
    Given I'm not logged in
    When I try to delete Take
    Then The response code is 401

  Scenario: Delete a Take that does not exist
    Given I login as "username" with password "password"
    When I try to delete Take with id 1000
    Then The response code is 404