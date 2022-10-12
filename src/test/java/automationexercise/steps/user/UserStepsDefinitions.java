package automationexercise.steps.user;

import automationexercise.allure.env.EnvironmentInfo;
import automationexercise.steps.BaseStepsDefenitions;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import ru.slava62.automationexercise.dto.MessageJSON;
import ru.slava62.automationexercise.dto.User;
import ru.slava62.automationexercise.service.UserService;
import ru.slava62.automationexercise.util.RetrofitUtils;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserStepsDefinitions extends BaseStepsDefenitions<MessageJSON, UserService>{

    // static ProductService service;
    // Response<Products> response;
    // Response<MessageJSON> response_message;

    @Before
    public void before_user(Scenario scenario) throws MalformedURLException {
    System.out.println("------------------------------"); 
    System.out.println("Starting - " + scenario.getName()); 
    System.out.println("------------------------------");
    service = RetrofitUtils.getUserService();
    create_set_account_data();
    }
 
    @After
    public void after(Scenario scenario) { 
        System.out.println("------------------------------"); 
        System.out.println("Ending - scenario"); 
        System.out.println("------------------------------");
        service = null;
        response=null;
        response_message=null;
        testUser=null;
        EnvironmentInfo.setAllureEnvironment();
    }

//    @SneakyThrows
//    @Given("the user has an account data")
    public void create_set_account_data() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        Faker usFaker = new Faker(new Locale("en-US"));

        Date date=usFaker.date().birthday();
//        LocalDate localDate
//                = LocalDate.parse(date.toString());
        testUser= new User()
                .withName(usFaker.name().username())
                .withEmail(fakeValuesService.bothify("????##@gmail.com"))//"326c6224@mailto.plus")
                .withPassword(fakeValuesService.bothify("?#??##??"))
                .withTitle(usFaker.funnyName().name())
                .withBirthDay(String.valueOf(date.getDay()))
                .withBirthMonth("May")
                .withBirthYear(String.valueOf(date.getYear()))
                .withFirstName(usFaker.name().firstName())
                .withLastName(usFaker.name().lastName())
                .withCompany(usFaker.company().name())
                .withAddress1(usFaker.address().fullAddress())
                .withAddress2(usFaker.address().secondaryAddress())
                .withCountry(usFaker.country().name())
                .withZipcode(usFaker.address().zipCode())
                .withState(usFaker.address().state())
                .withCity(usFaker.address().city())
                .withMobile_number(usFaker.phoneNumber().phoneNumber());
//        response = RetrofitUtils.getProductList(service);
    }
    @SneakyThrows
    @When("the user tries to create an account")
    public void user_tries_to_create_an_account() {
        response_message=RetrofitUtils.createUser(testUser, service);
    }
//    @SneakyThrows
//    @When("the user requests product {string}")
//    public void user_search_product(String product) {
////        response = RetrofitUtils.postProductSearch(product, service);
//    }
//    @SneakyThrows
//    @When("the user requests endpoint without parameter")
//    public void user_search_product_no_parameter() {
////        response_message = RetrofitUtils.postProductSearchNoParameter(service);
//    }
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
            assert response_message.body() != null;
            assertThat(responseCode, equalTo(response_message.body().getResponseCode()));}
    }
//    @And("the response JSON has products array")
//    public void the_user_check_JSON_productsArray() {
////        ArrayList<Product> products=(ArrayList<Product>)response.body().getProducts();
////        assertThat(products.size(), greaterThan(0));
//    }
//    @And("the response JSON has products array with category {string} only")
//    public void the_user_check_JSON_productsArray(String category) {
////        ArrayList<Product> products=(ArrayList<Product>)response.body().getProducts();
////        assertThat(products.size(), greaterThan(0));
////        boolean checker=false;
////        for (Product product : products) {
////            if(product.getCategory().getCategory().toUpperCase().contains(category.toUpperCase())){
////                checker=true;
////            }
////            else{checker=false;}
////        }
////        assertThat(checker, is(true));
//    }
    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) {
        assert response_message.body() != null;
        assertThat(message, equalTo(response_message.body().getMessage()));
    }

}
