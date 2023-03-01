Feature: Update a donation
  In order to be able to modify donation information
  As a User who has created a donation or an admin
  I want to be able to update a donation

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"

  Scenario: Update donation as the user who created it
    Given I can login with username "user" and password "password"
    And The response code is 200
    And I create a new valid donation
    And The response code is 201
    When I update the donation name
    Then The response code is 200

  Scenario: Update donation as an admin
    Given I can login with username "user" and password "password"
    And The response code is 200
    And I create a new valid donation
    And There is a registered admin with username "admin" and password "password" and email "admin@sample.app"
    And I can login with username "admin" and password "password"
    And The response code is 200
    When I update the donation name
      | name | New Donation Name |
    Then The response code is 200

  Scenario: Update donation as the user who didn't create it
    Given I can login with username "user" and password "password"
    And The response code is 200
    And I create a new valid donation
    And The response code is 201
    And There is a registered user with username "user2" and password "password" and email "user2@smaple.app"
    And I can login with username "user2" and password "password"
    And The response code is 200
    When I update the donation name
      | name | New Donation Name |
    Then The response code is 403