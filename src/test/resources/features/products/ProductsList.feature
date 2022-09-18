Feature: Products List

  # @ignored
  @positive
  @severity=critical
  Scenario: Get All Products List
    When the user requests all products
    Then the response code is 200
    And  the response JSON has responseCode 200
    And  the response JSON has products array
  
  # @ignored
  @negative
  Scenario: POST To All Products List
    Server returns response with status code 200
    and JSON with message that the method is not
    supported.
    When the user tries to post to product list
    Then the response code is 200
    And  the response JSON has responseCode 405
    And  the response JSON has message "This request method is not supported."