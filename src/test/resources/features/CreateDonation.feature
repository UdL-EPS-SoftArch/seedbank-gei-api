Feature: Create a Donation
  In order to be able to donate seeds
  As a Donor
  I want to be able to create a donation

  Background:
    Given There is a registered donor with username "user" and password "password" and email "user@user.com"

  Scenario: Create a new Donation
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is a donor
    And A take action exists
    When The donor can create a donation from the take action
    Then The response code is 201
    And There is 1 donation created

#  Scenario: Create a new Donation without being logged in
#    Given I'm not logged in
#    And There is a valid Donor with name "donor"
#    And There is a valid Take with identifier 1
#    When I create a new valid donation with id: 1 from donor "donor" and take 1
#    Then The response code is 401
#    And There is 0 donation created

  Scenario: Create a new Donation without a donor
    Given I can login with username "user" and password "password"
    And The response code is 200
    And A take action exists
    But There is no Donor
    When I create a new donation with the take action
    Then The response code is 400
    And There is 0 donation created

  Scenario: Create a new Donation without a take
    Given I can login with username "user" and password "password"
    And The response code is 200
    And User "user" is a donor
    But There is no Take
    When I create a new donation from the donor but without a take action
    Then The response code is 201
    And There is 1 donation created

