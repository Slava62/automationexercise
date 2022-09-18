package automationexercise.steps.brands;

import lombok.SneakyThrows;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import ru.slava62.automationexercise.service.*;
import ru.slava62.automationexercise.util.*;
import ru.slava62.automationexercise.dto.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;

import automationexercise.allure.env.EnvironmentInfo;
import automationexercise.steps.BaseStepsDefenitions;

import java.net.MalformedURLException;


public class BrandStepsDefinitions extends BaseStepsDefenitions<Brands,BrandService>{

    // static BrandService service;
    // Response<Brands> response;
    // Response<MessageJSON> response_message;

    @Before
    public void before_product(Scenario scenario) throws MalformedURLException { 
    System.out.println("------------------------------"); 
    System.out.println("Starting - " + scenario.getName()); 
    System.out.println("------------------------------");
    service = RetrofitUtils.getBrandService();
    }
 
    @After 
    public void after(Scenario scenario) { 
        System.out.println("------------------------------"); 
        System.out.println("Ending - scenario"); 
        System.out.println("------------------------------");
        service = null;
        response=null;
        response_message=null;
        EnvironmentInfo.setAllureEnvironment();
    }

    

    @SneakyThrows
    @When("the user requests all brands")
    public void user_requests_all_brands() {
        
        response = RetrofitUtils.getBrandList(service);
    }
    @SneakyThrows
    @When("the user tries to put to brands list")
    public void user_tries_put_to_brands_list() {
        response_message=RetrofitUtils.putBrandList(service);
    }
    @Then("the response code is 200")
    public void user_check_the_response_code() {
        //step("GET /repos/:owner/:repo/labels?text=" + "text");
        if (!(response==null)){
        assertThat(response.isSuccessful(), equalTo(true));}
        else{
        assertThat(response_message.isSuccessful(), equalTo(true));}    
        }
    @And("the response JSON has responseCode {int}")
    public void user_check_JSON_responseCode(int responseCode) {
        if (!(response==null)){
        assertThat(responseCode, equalTo(response.body().getResponseCode()));
        }
        else{
        assertThat(responseCode, equalTo(response_message.body().getResponseCode()));}
    }
    @And("the response JSON has brands array")
    public void the_user_check_JSON_brandssArray() {
        ArrayList<Brand> brands=(ArrayList<Brand>)response.body().getBrands(); 
        assertThat(brands.size(), greaterThan(0));
    }
    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) {
        assertThat(message, equalTo(response_message.body().getMessage()));
    }
}
