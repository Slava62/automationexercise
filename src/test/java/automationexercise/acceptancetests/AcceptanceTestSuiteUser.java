package automationexercise.acceptancetests;

// import io.cucumber.junit.platform.engine.Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/user_account",
plugin = { "pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},//"html:target/cucumber-reports.html" },
monochrome = false, 
glue="automationexercise.steps.user",
tags ="not @ignored and @use")// and @product and @message
public class AcceptanceTestSuiteUser { }
