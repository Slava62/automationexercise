package automationexercise.steps.products;

import lombok.SneakyThrows;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After
;
import ru.slava62.automationexercise.service.*;
import ru.slava62.automationexercise.util.*;
import ru.slava62.automationexercise.dto.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.MalformedURLException;
import java.util.ArrayList;

import automationexercise.allure.env.EnvironmentInfo;
// import static io.qameta.allure.Allure.step;
import automationexercise.steps.BaseStepsDefenitions;

public class ProductStepsDefinitions extends BaseStepsDefenitions<Products,ProductService>{

    // static ProductService service;
    // Response<Products> response;
    // Response<MessageJSON> response_message;

    @Before
    public void before_product(Scenario scenario) throws MalformedURLException { 
    System.out.println("------------------------------"); 
    System.out.println("Starting - " + scenario.getName()); 
    System.out.println("------------------------------");
    service = RetrofitUtils.getProductService(); 
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

    // @SneakyThrows
    // @Given("user tests automationexercise.com API")
    // static public void user_tests_API() {
    //     // productService = RetrofitUtils.getProductService();
    // }

    @SneakyThrows
    @When("the user requests all products")
    public void user_requests_all_products() {
        
        response = RetrofitUtils.getProductList(service);
    }
    @SneakyThrows
    @When("the user tries to post to product list")
    public void user_tries_post_to_product_list() {
        response_message=RetrofitUtils.postProductList(service);
    }
    @SneakyThrows
    @When("the user requests product {string}")
    public void user_search_product(String product) {
        response = RetrofitUtils.postProductSearch(product, service);
    }
    @SneakyThrows
    @When("the user requests endpoint without parameter")
    public void user_search_product_no_parameter() {
        response_message = RetrofitUtils.postProductSearchNoParameter(service);
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
    @And("the response JSON has products array")
    public void the_user_check_JSON_productsArray() {
        ArrayList<Product> products=(ArrayList<Product>)response.body().getProducts(); 
        assertThat(products.size(), greaterThan(0));
    }
    @And("the response JSON has products array with category {string} only")
    public void the_user_check_JSON_productsArray(String category) {
        ArrayList<Product> products=(ArrayList<Product>)response.body().getProducts(); 
        assertThat(products.size(), greaterThan(0));
        boolean checker=false;
        for (Product product : products) {
            if(product.getCategory().getCategory().toUpperCase().contains(category.toUpperCase())){
                checker=true;
            }
            else{checker=false;}
        }
        assertThat(checker, is(true));
    }
    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) {
        assertThat(message, equalTo(response_message.body().getMessage()));
    }

}
