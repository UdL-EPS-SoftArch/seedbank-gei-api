Feature: Create Seed
  In order to create Seed
  I want to create a new Seed
  
  Background: 
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"
    
  Scenario: Create a new Seed with valid scientific name and common name
    Given I can login with username "username" and password "password"
    When I create a new Seed with scientificName "Allium cepa" and commonName "Onion, Cebolla"
    Then The response code is 201
    And There is 1 Seed created
    And I try to retrieve Seed with scientificName "Allium cepa"
    And The response code is 200

  Scenario: Create a new Seed with valid scientific name and empty common name
    Given I can login with username "username" and password "password"
    When I create a new Seed with scientificName "Allium cepa" and commonName ""
    Then The response code is 201
    And There is 1 Seed created
    And I try to retrieve Seed with scientificName "Allium cepa"
    And The response code is 200

  Scenario: Create a new Seed with empty scientific name and valid common name
    Given I can login with username "username" and password "password"
    When I create a new Seed with scientificName "" and commonName "Onion, Cebolla"
    Then The response code is 400
    And There is 0 Seed created
    And I try to retrieve Seed with scientificName ""
    And The response code is 400

  Scenario: Create a new Seed with empty scientific name and common name
    Given I can login with username "username" and password "password"
    When I create a new Seed with scientificName "" and commonName ""
    Then The response code is 400
    And There is 0 Seed created
    And I try to retrieve Seed with scientificName ""
    And The response code is 400

  Scenario: Create a new Seed when I am not logged in
    Given I am not logged in
    When I create a new Seed with scientificName "Allium cepa" and commonName "Onion, Cebolla"
    Then The response code is 401
    And There is 0 Seed created
    And I try to retrieve Seed with scientificName "Allium cepa"
    And The response code is 400

  Scenario: Create a new Seed with empty body
    Given I login as "username" with password "password"
    When I create a new Seed with empty body
    Then The response code is 400
    And There is 0 Seed created