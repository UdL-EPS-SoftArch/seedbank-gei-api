Feature: Update a Donation
  In order to be able to update a donation
  As a user
  I want to be able to update a donation

  Background: 
    Given There is a registered donor with username "user" and password "password" and email "user@sample.app"

  Scenario: Update a donation successfully
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the donor
    And A valid take action exists
    And The donor creates a donation from the take action
    And The response code is 201
    When The donor updates the donation
    Then The response code is 200
    And There is 1 donation created
    And The new donation is updated

  Scenario: Update a donation with an empty body
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the donor
    And A valid take action exists
    And The donor creates a donation from the take action
    And The response code is 201
    When The donor updates the donation with an empty body
    Then The response code is 400
    And There is 1 donation created
    And The new donation is not updated