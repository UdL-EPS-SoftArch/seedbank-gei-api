Feature: Get Seed
  In order to get Seed
  I want to get a Seed


  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a seed with id 1, scientificName "Allium cepa" and commonName "Onion, Cebolla"

  Scenario: Get a Seed
    Given I login as "username" with password "password"
    And I create a new Seed with scientificName "Allium cepa" and commonName "Onion, Cebolla"
    When I try to retrieve a Seed with id 1
    Then The response code is 200

  Scenario: Get a Seed which does not exist
    Given I login as "username" with password "password"
    When I try to retrieve a Seed with id 1
    Then The response code is 404
