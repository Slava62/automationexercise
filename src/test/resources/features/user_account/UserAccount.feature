Feature: User account
  Operation with user account
  @ignored
  @positive
  Scenario: POST To Create/Register User Account
    Given the user has an account data
    When the user tries to create an account
    Then the response code is 200
    And  the response JSON has responseCode 201
    And  the response JSON has message "User created!"
  @ignored
  @positive
  Scenario: PUT METHOD To Update User Account
    Given the user has an account data to update
    When the user tries to update an account
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has message "User updated!"
  @ignored
  @positive
  Scenario: GET user account detail by email
    Given the user has an account email
    When the user tries to get the account detail
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has the account detail
  @ignored
  @positive
  Scenario: DELETE METHOD To Delete User Account
    Given the user has an account email and password
    When the user tries to delete an account
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has message "Account deleted!"