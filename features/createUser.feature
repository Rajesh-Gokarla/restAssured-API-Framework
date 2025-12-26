
Feature: Create User API

  Scenario: Create a user successfully
    
    Given I generate auth token
    When I create a user
    Then the user should be created successfully