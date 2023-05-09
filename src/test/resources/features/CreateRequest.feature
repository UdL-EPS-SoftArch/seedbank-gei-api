Feature: Create a Request
  In order to be able to receive seeds
  As a Propagator
  I want to be able to create a request

  Background:
    Given There is a registered propagator with username "user" and password "password" and email "user@user.com"

  Scenario: Create a new Request
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the propagator
    And A valid take action exists for the propagator
    When The propagator creates a request from the take action
    Then The response code is 201
    And There is 1 request created

  Scenario: Create a new Request without being logged in
    Given I'm not logged in
    And User "user" is the propagator
    And A valid take action exists for the propagator
    When The propagator creates a request from the take action
    Then The response code is 401
    And There is 0 request created

  Scenario: Create a new Request without a propagator
    Given I can login with username "user" and password "password"
    And The response code is 200
    And A valid take action exists for the propagator
    But There is no Propagator
    When The propagator creates a request from the take action
    Then The response code is 412
    And There is 0 request created

Scenario: Create a new Request without a take
  Given I can login with username "user" and password "password"
  And The response code is 200
  And User "user" is the propagator
  But There is no Take
  When The propagator creates a request from the take action
  Then The response code is 201
  And There is 1 request created


