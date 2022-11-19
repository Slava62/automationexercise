package automationexercise.steps.user;

import automationexercise.allure.env.EnvironmentInfo;
import automationexercise.steps.BaseStepsDefenitions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Response;
import ru.slava62.automationexercise.dto.MessageJSON;
import ru.slava62.automationexercise.dto.User;
import ru.slava62.automationexercise.service.UserService;
import ru.slava62.automationexercise.util.ConfigUtils;
import ru.slava62.automationexercise.util.RetrofitUtils;

import java.io.IOException;
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

public class UserStepsDefinitions {
    @Before
    public void before_user(Scenario scenario) throws MalformedURLException {
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

    TestContext context;
    User testUser;
    UserService service;

    public UserStepsDefinitions(TestContext testContext) throws MalformedURLException {
         this.context=testContext;
        service=testContext.getUserService();
        testUser=testContext.getTestUser();
    }

    @SneakyThrows
    @When("the user tries to create an account")
    public void user_tries_to_create_an_account() {
        ConfigUtils.setEMail(testUser.getEmail());
        ConfigUtils.setPassword(testUser.getPassword());
        context.setResponse(RetrofitUtils.createUser(testUser, service));
//        System.out.println("Creating - " + testUser.getEmail());
    }
    @SneakyThrows
    @When("the user with email and password tries to delete an account")
    public void user_tries_to_delete_an_account() {
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        context.setResponse(RetrofitUtils.deleteUser(testUser, service));
//        System.out.println("Deleting - " + testUser.getEmail());
    }
    @SneakyThrows
    @When("the user tries to login")
    public void user_tries_to_login(){
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        context.setResponse(RetrofitUtils.loginUser(testUser, service));
    }
    @SneakyThrows
    @When("the user tries to login without email parameter")
    public void user_has_no_email_to_login(){
        testUser.setPassword(ConfigUtils.getPassword());
        context.setResponse(RetrofitUtils.loginUser(testUser.getPassword(),service));
    }
    @SneakyThrows
    @When("the user tries to use DELETE method")
    public void user_sends_delete_request(){
        context.setResponse(RetrofitUtils.deleteMethod(service));
    }
    @SneakyThrows
    @When("the user tries to login with invalid details")
    public void user_tries_to_use_ivnalid_details(){
        testUser.setEmail("invalid_email");
        testUser.setPassword("invalid_password");
        context.setResponse(RetrofitUtils.loginUser(testUser,service));
    }
    @SneakyThrows
    @When("the user tries to update an account")
    public void user_tries_to_update_account(){
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        context.setResponse(RetrofitUtils.updateUser(testUser,service));
    }
    @SneakyThrows
    @When("the user tries to get the account detail by email")
    public void user_gets_the_account_detail(){
        testUser.setEmail(ConfigUtils.getEMail());
        context.setResponse(RetrofitUtils.getUserDetails(testUser,service));
    }
    @Then("the response code is 200")
    public void user_check_the_response_code() {
        assertThat(context.getResponse().isSuccessful(), equalTo(true));//}
        }
    @And("the response JSON has responseCode {int}")
    public void user_check_JSON_responseCode(int responseCode) throws IOException {
        JSONObject myObject = context.getJSONObject();
        assertThat(responseCode, equalTo((int)myObject.get("responseCode")));
    }

    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) throws IOException {
        JSONObject myObject = context.getJSONObject();
        String s=myObject.get("message").toString();
        System.out.println(s);
        assertThat(message, equalTo(myObject.get("message")));
    }
    @And("the response body has responseCode {int}")
    public void the_user_check_responseCode(int responseCode) throws IOException {
            Response<ResponseBody> response=context.getResponse();
            String result=response.body().string();
            JSONObject myObject = context.getJSONObject();
            ObjectMapper mapper = new ObjectMapper();
            User user=mapper.readValue( myObject.get("user").toString(),User.class);
            assertThat(responseCode, equalTo((int)myObject.get("responseCode")));
            assertThat(ConfigUtils.getEMail(), equalTo(user.getEmail()));

    }

}
