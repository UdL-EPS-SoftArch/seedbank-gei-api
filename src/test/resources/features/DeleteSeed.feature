Feature: Delete Seed
  In order to delete Seed
  I want to delete a Seed

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"

  Scenario: Delete a Seed with user logged in
    Given I login as "username" with password "password"
    And There is a created Seed with scientificName "Allium cepa" and commonName "Onion"
    When I delete Seed
    Then the response code is 204
    And I try to retrieve that Seed
    And the response code is 404

  Scenario: Delete a Seed with user not logged in
    Given I'm not logged in
    And There is a created Seed with scientificName "Allium cepa" and commonName "Onion"
    When I delete Seed
    Then The response code is 401

  Scenario: Delete a Seed with valid scientificName and empty common name
    Given I login as "username" with password "password"
    And There is a created Seed with scientificName "Allium cepa" and commonName ||
    When I delete Seed
    Then the response code is 204
    And I try to retrieve that Seed
    And the response code is 404