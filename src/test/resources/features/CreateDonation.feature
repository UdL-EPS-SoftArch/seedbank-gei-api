Feature: Create a Donation
  In order to be able to donate seeds
  As a Donor
  I want to be able to create a donation

  Background:
    Given There is a registered donor with username "user" and password "password" and email "user@user.com"

  Scenario: Create a new Donation
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the donor
    And A valid take action exists
    When The donor creates a donation from the take action
    Then The response code is 201
    And There is 1 donation created

  Scenario: Create a new Donation without being logged in
    Given I'm not logged in
    And User "user" is the donor
    And A valid take action exists
    When The donor creates a donation from the take action
    Then The response code is 401
    And There is 0 donation created

  Scenario: Create a new Donation without a donor
    Given I can login with username "user" and password "password"
    And The response code is 200
    And A valid take action exists
    But There is no Donor
    When The donor creates a donation from the take action
    Then The response code is 412
    And There is 0 donation created

  Scenario: Create a new Donation without a take
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is the donor
    But There is no Take
    When The donor creates a donation from the take action
    Then The response code is 201
    And There is 1 donation created

  Scenario: Create a new Donation with an existing Request
    Given I can login with username "user" and password "password"
    Given There is not taken request created
    And The response code is 200
    And User "user" is the donor
    When Donor creates the donation from the request
    Then Take is automatically created
