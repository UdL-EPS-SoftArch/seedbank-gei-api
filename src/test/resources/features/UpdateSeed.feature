Feature: Update Seed
  In order to update Seed
  I want to update a new Seed

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
  Scenario: Update a Seed with valid scientific name
    Given I login as "username" with password "password"
    And I create a new Seed with scientificName "Allium cepa" and commonName | "Onion" |
    Then The response code is 200
    When I update Seed by changing scientificName to "Allium Cepa"
    Then The response code is 200
    And There is 1 Seed updated

  Scenario: Update a Seed with empty scientific name
    Given I login as "username" with password "password"
    And I create a new Seed with scientificName "Allium cepa" and commonName | "Onion" |
    Then The response code is 200
    When I update Seed by changing scientificName to ""
    Then The response code is 400
    And There is 0 Seed updated

  Scenario: Update a Seed with valid scientific name and valid common name
    Given I login as "username" with password "password"
    And I create a new Seed with scientificName "Allium cepa" and commonName | "Onion" |
    Then The response code is 200
    When I update Seed by changing scientificName to "Allium Cepa" and commonName to | "Onion" | "Cebolla" |
    Then The response code is 200
    And There is 1 Seed updated

  Scenario: Update a Seed with valid common name
    Given I login as "username" with password "password"
    And I create a new Seed with scientificName "Allium cepa" and commonName | "Onion" |
    Then The response code is 200
    When I update Seed by changing commonName to | "Onion" | "Cebolla" |
    Then The response code is 200
    And There is 1 Seed updated

  Scenario: Update a Seed with empty body
    Given I login as "username" with password "password"
    And I create a new Seed with scientificName "Allium cepa" and commonName | "Onion" |
    Then The response code is 200
    When I update Seed with empty body
    Then The response code is 400
    And There is 0 Seed updated

  Scenario: Update a Seed when I am not logged in
    Given I am not logged in
    And I create a new Seed with scientificName "Allium cepa" and commonName | "Onion" |
    Then The response code is 200
    When I update Seed by changing scientificName to "Allium Cepa" and commonName to | "Onion" | "Cebolla" |
    Then The response code is 401
    And There is 0 Seed updated