Feature: Update Seed
  In order to update Seed
  I want to update a new Seed

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a Seed with id 1, scientificName "Allium cepa" and commonName "Onion, Cebolla"

  Scenario: Update a Seed
    Given I login as "username" with password "password"
    When I update Seed with id 1 by changing scientificName to "Allium Cepa" and commonName to "Cebolla"
    Then The response code is 200

  Scenario: Update a Seed with invalid content
    Given I login as "username" with password "password"
    When I update Seed with id 1 by changing scientificName to ""
    Then The response code is 400

  Scenario: Update a Seed when I am not logged in
    Given I'm not logged in
    When I update Seed with id 1 by changing scientificName to "Allium Cepa" and commonName to "Onion, Cebolla"
    Then The response code is 401
