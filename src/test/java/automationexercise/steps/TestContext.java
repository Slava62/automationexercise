package automationexercise.steps;


import automationexercise.allure.env.EnvironmentInfo;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Response;
import ru.slava62.automationexercise.dto.User;
import ru.slava62.automationexercise.service.BrandService;
import ru.slava62.automationexercise.service.ProductService;
import ru.slava62.automationexercise.service.UserService;
import ru.slava62.automationexercise.util.RetrofitUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestContext {

    @Before(order=0)
    public void beforeScenario(Scenario scenario) throws MalformedURLException {
        System.out.println("------------------------------");
        System.out.println("Starting - " + scenario.getName());
        System.out.println("------------------------------");
    }
    @After(order=0)
    public void afterScenario(Scenario scenario) {
        System.out.println("------------------------------");
        System.out.println("Ending - scenario");
        System.out.println("------------------------------");
        EnvironmentInfo.setAllureEnvironment();
    }
    private Response<ResponseBody> response;

    private JSONObject myObject;
    private User testUser;

    public TestContext() throws MalformedURLException {
        testUser= create_set_account_data();
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) throws IOException {
        this.response = response;
        String result=this.response.body().string();
        myObject=new JSONObject(result);
    }
    public UserService getUserService() throws MalformedURLException {
        return RetrofitUtils.getUserService();
    }
    public ProductService getProductService() throws MalformedURLException {
        return RetrofitUtils.getProductService();
    }
    public BrandService getBrandService() throws MalformedURLException {
        return RetrofitUtils.getBrandService();
    }
    public User getTestUser() {
        return testUser;
    }
    private User create_set_account_data() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        Faker usFaker = new Faker(new Locale("en-US"));
        Date date=usFaker.date().birthday();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String st=new SimpleDateFormat("MMMM", Locale.forLanguageTag("en-GB")).format(date);
        return new User()
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
    public JSONObject getJSONObject()  {
        return myObject;
    }

    @Then("the response code is 200")
    public void user_check_the_response_code() {
        assertThat(this.getResponse().isSuccessful(), equalTo(true));
    }
    @And("the response JSON has responseCode {int}")
    public void user_check_JSON_responseCode(int responseCode) {
        JSONObject myObject = this.getJSONObject();
        assertThat(responseCode, equalTo((int)myObject.get("responseCode")));
    }
    @And("the response JSON has message {string}")
    public void the_user_check_JSON_message(String message) {
        JSONObject myObject = this.getJSONObject();
        String s=myObject.get("message").toString();
        assertThat(message, equalTo(myObject.get("message")));
    }
}
