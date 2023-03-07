Feature: Get Take
  In order to get Take
  I want to get a Take


  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
  
  Scenario: Get a new Take
    Given I login as "username" with password "password"
    And I create a new Take with amount 5, weight 5 and location "Lleida"
    When I retrieve that Take
    Then The response code is 200
