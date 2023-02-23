Feature: Create a Donation
  In order to be able to donate seeds
  As a Donor
  I want to be able to create a donation

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"

  Scenario: Create a new Donation
    Given I can login with username "user" and password "password"
    And The response code is 200
    And There is a valid Donor
    And There is a valid Take
    When I create a new donation
    Then The response code is 201
    And There is 1 donation created

  Scenario: Create a new Donation without being logged in
    Given I'm not logged in
    And There is a valid Donor
    And There is a valid Take
    When I create a new donation
    Then The response code is 401
    And There is 0 donation created

  Scenario: Create a new Donation without a donor
    Given I can login with username "user" and password "password"
    And The response code is 200
    And There is a valid Take
    But There is no valid Donor
    When I create a new donation
    Then The response code is 400

  Scenario: Create a new Donation without a take
    Given I can login with username "user" and password "password"
    And The response code is 200
    And There is a valid Donor
    But There is no valid Take
    When I create a new donation
    Then The response code is 400

