Feature: Brands List

  # @ignored
  @positive
  @severity=critical
  Scenario: Get All Brands List
    When the user requests all brands
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has brands array 
  
  # @ignored
  @negative
  Scenario: PUT To All Brands List
    When the user tries to put to brands list
    Then the response code is 200
    And  the response JSON has responseCode 405
    And  the response JSON has message "This request method is not supported."