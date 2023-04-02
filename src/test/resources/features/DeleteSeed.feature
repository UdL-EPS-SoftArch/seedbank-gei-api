Feature: Delete Seed
  In order to delete Seed
  I want to delete a Seed

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a Seed with id 1, scientificName "Allium cepa" and commonName "Onion, Cebolla"

  Scenario: Delete a Seed with user logged in
    Given I login as "username" with password "password"
    When I delete Seed with id 1
    Then The response code is 200

  Scenario: Delete a Seed with user not logged in
    Given I'm not logged in
    When I delete Seed with id 1
    Then The response code is 401

  Scenario: Delete a Seed that doesn't exist
    Given I login as "username" with password "password"
    When I delete Seed with id -1
    Then The response code is 404