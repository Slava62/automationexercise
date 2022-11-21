package automationexercise.steps.products;

import automationexercise.steps.TestContext;
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
import java.io.IOException;
import java.net.MalformedURLException;


public class ProductStepsDefinitions {

    TestContext context;
    ProductService service;
    public ProductStepsDefinitions(TestContext testContext) throws MalformedURLException {
        this.context = testContext;
        service=testContext.getProductService();
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
    @And("the response JSON has products array")
    public void the_user_check_JSON_productsArray() throws IOException {
        JSONObject myObject = context.getJSONObject();
        ObjectMapper mapper = new ObjectMapper();
        Product[] products=mapper.readValue(myObject.get("products").toString(),Product[].class);
        assertThat(products.length, greaterThan(0));
    }
    @And("the response JSON has products array with category {string} only")
    public void the_user_check_JSON_productsArray(String category) throws IOException {
        JSONObject myObject = context.getJSONObject();
        ObjectMapper mapper = new ObjectMapper();
        Product[] products=mapper.readValue(myObject.get("products").toString(),Product[].class);
        boolean checker=false;
        for (Product product : products) {
            if(product.getCategory().getCategory().toUpperCase().contains(category.toUpperCase())){
                checker=true;
            }
            else{checker=false;}
        }
        assertThat(checker, is(true));
    }

}
