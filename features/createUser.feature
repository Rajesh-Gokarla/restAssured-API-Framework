
Feature: Create User API

  Scenario: Create a user successfully
    
    When I create a user
    Then the user should be created successfully