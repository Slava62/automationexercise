package automationexercise.acceptancetests;

// import io.cucumber.junit.platform.engine.Cucumber;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/products",
plugin = { "pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},//"html:target/cucumber-reports.html" },
monochrome = false, 
glue="automationexercise.steps.products",
tags ="not @ignored")// and @product and @message and @use
public class AcceptanceTestSuiteProduct { }
