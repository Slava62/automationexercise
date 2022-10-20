package automationexercise.steps;

import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.slava62.automationexercise.dto.*;
import ru.slava62.automationexercise.service.ProductService;
import ru.slava62.automationexercise.util.RetrofitUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;

import automationexercise.allure.env.EnvironmentInfo;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import java.net.MalformedURLException;


public class BaseStepsDefenitions<T,S> {
    protected Response<T> response;
    protected Response<MessageJSON> response_message;
    protected S service;
    protected User testUser;
    // protected void getService() throws MalformedURLException { 
    // if (service.getClass()==ProductService.class){
    // service = (S) RetrofitUtils.getProductService();}
    // else{
    // service =(S) RetrofitUtils.getBrandService();
    // } 
    // }
    
}