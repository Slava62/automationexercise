Feature: Products Search
  
  @positive
  Scenario Outline: Search for product
    When the user requests product '<category>'
    Then the response code is 200
    And  the response JSON has responseCode 200 
    And  the response JSON has products array with category '<category>' only
    Examples:
            |    category    |
            |     dress      |
            |     top        |

  @negative
  Scenario: Search for product without search_product parameter
    When the user requests endpoint without parameter
    Then the response code is 200
    And  the response JSON has responseCode 400 
    And  the response JSON has message "Bad request, search_product parameter is missing in POST request."