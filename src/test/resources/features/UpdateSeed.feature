Feature: Update Seed
  In order to update Seed
  I want to update a new Seed

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a seed with scientificName "Allium cepa" and commonName "Onion, Cebolla"

  Scenario: Update a Seed
    Given I login as "username" with password "password"
    When I update seed with scientific name "Allium cepa" by changing it to "Allium Cepa" and commonName to "Cebolla, onion"
    Then The response code is 200

  Scenario: Update a Seed with invalid content
    Given I login as "username" with password "password"
    When I update seed with scientific name "Allium cepa" by changing it to ""
    Then The response code is 400

  Scenario: Update a Seed when I am not logged in
    Given I'm not logged in
    When I update seed with scientific name "Allium cepa" by changing it to "Allium Cepa" and commonName to "Cebolla, onion"
    Then The response code is 401

  Scenario: Update a Seed that does not exist
    Given I login as "username" with password "password"
    When I update seed with scientific name "Nothing here" by changing it to "Allium Cepa" and commonName to "Cebolla, onion"
    Then The response code is 404
