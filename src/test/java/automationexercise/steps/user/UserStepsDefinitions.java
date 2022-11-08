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
import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.slava62.automationexercise.dto.MessageJSON;
import ru.slava62.automationexercise.dto.User;
import ru.slava62.automationexercise.service.UserService;
import ru.slava62.automationexercise.util.ConfigUtils;
import ru.slava62.automationexercise.util.RetrofitUtils;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserStepsDefinitions extends BaseStepsDefenitions<MessageJSON, UserService>{

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
        responseBody=null;
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String st=new SimpleDateFormat("MMMM", Locale.forLanguageTag("en-GB")).format(date);
        testUser= new User()
                .withName(usFaker.name().username())
                .withEmail(fakeValuesService.bothify("????##@gmail.com"))//"326c6224@mailto.plus")
                .withPassword(fakeValuesService.bothify("?#??##??"))
                .withTitle(usFaker.funnyName().name())
                .withBirthDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)))
                .withBirthMonth(st)
                .withBirthYear(String.valueOf(cal.get(Calendar.YEAR)))
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
    }
    @SneakyThrows
    @When("the user tries to create an account")
    public void user_tries_to_create_an_account() {
        ConfigUtils.setEMail(testUser.getEmail());
        ConfigUtils.setPassword(testUser.getPassword());
        response_message=RetrofitUtils.createUser(testUser, service);
    }
    @SneakyThrows
    @When("the user with email and password tries to delete an account")
    public void user_tries_to_delete_an_account() {
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        response_message = RetrofitUtils.deleteUser(testUser, service);
    }
    @SneakyThrows
    @When("the user tries to login")
    public void user_tries_to_login(){
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        response_message = RetrofitUtils.loginUser(testUser, service);
    }
    @SneakyThrows
    @When("the user tries to login without email parameter")
    public void user_has_no_email_to_login(){
        testUser.setPassword(ConfigUtils.getPassword());
        response_message=RetrofitUtils.loginUser(testUser.getPassword(),service);
    }
    @SneakyThrows
    @When("the user tries to use DELETE method")
    public void user_sends_delete_request(){
        response_message=RetrofitUtils.deleteMethod(service);
    }
    @SneakyThrows
    @When("the user tries to login with invalid details")
    public void user_tries_to_use_ivnalid_details(){
        testUser.setEmail("invalid_email");
        testUser.setPassword("invalid_password");
        response_message=RetrofitUtils.loginUser(testUser,service);
    }
    @SneakyThrows
    @When("the user tries to update an account")
    public void user_tries_to_update_account(){
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        response_message=RetrofitUtils.updateUser(testUser,service);
    }
    @SneakyThrows
    @When("the user tries to get the account detail by email")
    public void user_gets_the_account_detail(){
        testUser.setEmail(ConfigUtils.getEMail());
        responseBody=RetrofitUtils.getUserDetails(testUser,service);
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
            assert response_message.body() != null;

//            assertThat(responseCode, equalTo(response_message.body().getResponseCode()));
            }
    }

    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) {
        assert response_message.body() != null;
        assertThat(message, equalTo(response_message.body().getMessage()));
    }
    @And("the response body has responseCode {int}")
    public void the_user_check_responseCode(int responseCode) {
        if (!(responseBody==null)){
//        assert response_message.body() != null;
        assertThat(responseCode, equalTo(responseBody.code()));
    }
    }

}
