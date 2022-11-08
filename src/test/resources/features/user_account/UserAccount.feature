Feature: User account
  Operation with user account
#  @ignored
  @positive
  @use
  @one
  Scenario: POST To Create/Register User Account
#    Given the user has an account data
    When the user tries to create an account
    Then the response code is 200
    And  the response JSON has responseCode 201
    And  the response JSON has message "User created!"
#  @ignored
  @positive
  @use
  Scenario: POST To Verify Login with valid details
    #Given the user has valid account details
    When the user tries to login
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has message "User exists!"
#  @ignored
  @negative
  @use
  Scenario: POST To Verify Login without email parameter
    When the user tries to login without email parameter
    Then the response code is 200
    And  the response JSON has responseCode 400
    And  the response JSON has message "Bad request, email or password parameter is missing in POST request."
#  @ignored
  @negative
  @use
  Scenario: DELETE To Verify Login
    When the user tries to use DELETE method
    Then the response code is 200
    And  the response JSON has responseCode 405
    And  the response JSON has message "This request method is not supported."
#  @ignored
  @negative
  @use
  Scenario: POST To Verify Login with invalid details
    When the user tries to login with invalid details
    Then the response code is 200
    And  the response JSON has responseCode 404
    And  the response JSON has message "User not found!"
#  @ignored
  @positive
  @use
  Scenario: PUT METHOD To Update User Account
    When the user tries to update an account
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has message "User updated!"
#  @ignored
  @positive
  @use
  @one
  Scenario: GET user account detail by email
    When the user tries to get the account detail by email
#    Then the response code is 200
    Then  the response body has responseCode 200
#    And  the response JSON has the account detail
#  @ignored
  @positive
  @use
  @one
  Scenario: DELETE METHOD To Delete User Account
    When the user with email and password tries to delete an account
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has message "Account deleted!"