package automationexercise.steps.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.TestContext;
import lombok.SneakyThrows;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After
;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Response;
import ru.slava62.automationexercise.service.*;
import ru.slava62.automationexercise.util.*;
import ru.slava62.automationexercise.dto.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import automationexercise.allure.env.EnvironmentInfo;
// import static io.qameta.allure.Allure.step;
import automationexercise.steps.BaseStepsDefenitions;

public class ProductStepsDefinitions {

    TestContext context;
    ProductService service;
    public ProductStepsDefinitions(TestContext testContext) throws MalformedURLException {
        this.context = testContext;
        service=testContext.getProductService();
    }

    @Before
    public void before_product(Scenario scenario) throws MalformedURLException { 
    System.out.println("------------------------------"); 
    System.out.println("Starting - " + scenario.getName()); 
    System.out.println("------------------------------");
    }
 
    @After
    public void after(Scenario scenario) { 
        System.out.println("------------------------------"); 
        System.out.println("Ending - scenario"); 
        System.out.println("------------------------------");
        EnvironmentInfo.setAllureEnvironment();
    }

    @SneakyThrows
    @When("the user requests all products")
    public void user_requests_all_products() {
        context.setResponse(RetrofitUtils.getProductList(service));
    }
    @SneakyThrows
    @When("the user tries to post to product list")
    public void user_tries_post_to_product_list() {
        context.setResponse(RetrofitUtils.postProductList(service));
    }
    @SneakyThrows
    @When("the user requests product {string}")
    public void user_search_product(String product) {
        context.setResponse(RetrofitUtils.postProductSearch(product, service));
    }
    @SneakyThrows
    @When("the user requests endpoint without parameter")
    public void user_search_product_no_parameter() {
        context.setResponse(RetrofitUtils.postProductSearchNoParameter(service));
    }
    @Then("the response code is 200")
    public void user_check_the_response_code() {
        assertThat(context.getResponse().isSuccessful(), equalTo(true));
        }
    @And("the response JSON has responseCode {int}")
    public void user_check_JSON_responseCode(int responseCode) throws IOException {
        Response<ResponseBody> response=context.getResponse();
        String result=response.body().string();
        JSONObject myObject = new JSONObject(result);
        assertThat(responseCode, equalTo((int)myObject.get("responseCode")));
    }
    @And("the response JSON has products array")
    public void the_user_check_JSON_productsArray() throws IOException {
        Response<ResponseBody> response=context.getResponse();
        String result=response.body().string();
        JSONObject myObject = new JSONObject(result);
        ObjectMapper mapper = new ObjectMapper();
        Products products=mapper.readValue(myObject.get("products").toString(),Products.class);
        assertThat(products.getProducts().size(), greaterThan(0));
    }
    @And("the response JSON has products array with category {string} only")
    public void the_user_check_JSON_productsArray(String category) throws IOException {
        Response<ResponseBody> response=context.getResponse();
        String result=response.body().string();
        JSONObject myObject = new JSONObject(result);
        ObjectMapper mapper = new ObjectMapper();
        Products products=mapper.readValue(myObject.get("products").toString(),Products.class);
        boolean checker=false;
        for (Product product : products.getProducts()) {
            if(product.getCategory().getCategory().toUpperCase().contains(category.toUpperCase())){
                checker=true;
            }
            else{checker=false;}
        }
        assertThat(checker, is(true));
    }
    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) throws IOException {
        Response<ResponseBody> response=context.getResponse();
        String result=response.body().string();
        JSONObject myObject = new JSONObject(result);
        ObjectMapper mapper = new ObjectMapper();
        MessageJSON messageJSON=mapper.readValue(myObject.get("message").toString(),MessageJSON.class);
        assertThat(message, equalTo(messageJSON.getMessage()));
    }

}
