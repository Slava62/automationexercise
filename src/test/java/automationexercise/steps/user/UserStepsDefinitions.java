package automationexercise.steps.user;

import automationexercise.steps.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.json.JSONObject;
import ru.slava62.automationexercise.dto.User;
import ru.slava62.automationexercise.service.UserService;
import ru.slava62.automationexercise.util.ConfigUtils;
import ru.slava62.automationexercise.util.RetrofitUtils;
import java.net.MalformedURLException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserStepsDefinitions {

    TestContext context;
    User testUser;
    UserService service;

    public UserStepsDefinitions(TestContext testContext) throws MalformedURLException {
        this.context=testContext;
        this.service=testContext.getUserService();
        this.testUser=testContext.getTestUser();
    }

    @SneakyThrows
    @When("the user tries to create an account")
    public void user_tries_to_create_an_account() {
        ConfigUtils.setEMail(testUser.getEmail());
        ConfigUtils.setPassword(testUser.getPassword());
        context.setResponse(RetrofitUtils.createUser(testUser, service));
    }
    @SneakyThrows
    @When("the user with email and password tries to delete an account")
    public void user_tries_to_delete_an_account() {
        testUser.setEmail(ConfigUtils.getEMail());
        testUser.setPassword(ConfigUtils.getPassword());
        context.setResponse(RetrofitUtils.deleteUser(testUser, service));
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

    @And("the response body has an account detail")
    public void the_user_check_responseCode() throws JsonProcessingException {
            JSONObject myObject = context.getJSONObject();
            ObjectMapper mapper = new ObjectMapper();
            User user=mapper.readValue( myObject.get("user").toString(),User.class);
            assertThat(ConfigUtils.getEMail(), equalTo(user.getEmail()));

    }

}
