package cucumber;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Response;
import ru.slava62.automationexercise.dto.MessageJSON;
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

public class TestContext {

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
}
