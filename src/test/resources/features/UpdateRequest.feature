Feature: Update a Request
  In order to be able to modify a request
  As a user
  I want to be able to update a request

  Background:
    Given There is a registered propagator with username "user" and password "password" and email "user@sample.app"

  Scenario: Update a request successfully
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the propagator
    And A valid take action exists for the propagator
    And The propagator creates a request from the take action
    And The response code is 201
    When The propagator updates the request
    Then The response code is 200
    And There is 1 request created
    And The new request is updated