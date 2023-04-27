Feature: Retrieve Request
  In order to see request
  As a user
  I want to retrieve requests

  Background:
    Given There is a registered propagator with username "user" and password "password" and email "user@sample.app"
    And I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the propagator
    And A valid take action exists for the propagator
    And The propagator creates a request from the take action
    And The response code is 201
    And The propagator creates a request from the take action
    And The response code is 201

  Scenario: List all requests
    When I retrieve all requests
    Then The response code is 200
    And The response contains 2 requests