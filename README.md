# Automation exercise project

Test cases from [here](https://automationexercise.com/api_list) are realised in this project with using Cucumber, JUnit 5 and Allure-reports. 

## Running the tests under Maven

To run the tests with Maven, open a command window and run:

    mvn clean integration-test allure:serve
    
##  or

    mvn clean verify
    
    cd target
    
    allure serve
