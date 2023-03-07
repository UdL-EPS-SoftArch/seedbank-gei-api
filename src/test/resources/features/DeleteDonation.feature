Feature: Delete a Donation
  In order to be able to remove a donation
  As a Donor
  I want to be able to remove a donation

  Background:
    Given There is a registered donor with username "user" and password "password" and email "user@user.com"

  Scenario: Remove a Donation
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the donor
    And A valid take action exists
    And The donor creates a donation from the take action
    And The response code is 201
    When The donor removes the donation
    Then The response code is 204
    And There is 0 donation created
