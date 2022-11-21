package automationexercise.steps.brands;

import automationexercise.steps.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.json.JSONObject;
import ru.slava62.automationexercise.service.*;
import ru.slava62.automationexercise.util.*;
import ru.slava62.automationexercise.dto.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.net.MalformedURLException;


public class BrandStepsDefinitions {

    TestContext context;
    BrandService service;

    public BrandStepsDefinitions(TestContext testContext) throws MalformedURLException {
        this.context = testContext;
        this.service=context.getBrandService();

    }
    @SneakyThrows
    @When("the user requests all brands")
    public void user_requests_all_brands() {
        context.setResponse(RetrofitUtils.getBrandList(service));
    }
    @SneakyThrows
    @When("the user tries to put to brands list")
    public void user_tries_put_to_brands_list() {
        context.setResponse(RetrofitUtils.putBrandList(service));
    }
    @And("the response JSON has brands array")
    public void the_user_check_JSON_brandssArray() throws JsonProcessingException {
        JSONObject myObject = context.getJSONObject();
        ObjectMapper mapper = new ObjectMapper();
        Brand[] brands = mapper.readValue(myObject.get("brands").toString(), Brand[].class);
        assertThat(brands.length, greaterThan(0));
    }

}
