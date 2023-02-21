Feature: Make Donation
  In order to use the app
  As a donor
  I want to be able to make a donation

  Scenario: Make a new donation
    Given There is a donor with name "user"
    When I I donate a batch with id "1"
    Then The response code is 200
    And It has created a donation associated with the batch with id "1"