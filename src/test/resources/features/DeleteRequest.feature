Feature: Delete Request
  In order to remove a request
  As a user
  I want to delete a request

  Background:
    Given There is a registered propagator with username "user" and password "password" and email "user@sample.app"

  Scenario: Delete a Request successfully
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the propagator
    And A valid take action exists for the propagator
    And The propagator creates a request from the take action
    And The response code is 201
    When The propagator removes the request
    Then The response code is 204
    And There is 0 request created