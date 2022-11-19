package cucumber;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.slava62.automationexercise.dto.MessageJSON;
import ru.slava62.automationexercise.dto.User;
import ru.slava62.automationexercise.service.UserService;
import ru.slava62.automationexercise.util.RetrofitUtils;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TestContext {

    private Response<MessageJSON> response;

    public Response<ResponseBody> getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Response<ResponseBody> responseBody) {
        this.responseBody = responseBody;
    }

    private Response<ResponseBody> responseBody;
//    protected Response<MessageJSON> response_message;
    private UserService service;
    private User testUser;

    public TestContext() throws MalformedURLException {
        testUser= create_set_account_data();
        service = RetrofitUtils.getUserService();
        response=null;
        responseBody=null;
    }

    public Response<MessageJSON> getResponse() {
        return response;
    }

    public void setResponse(Response<MessageJSON> response) {
        this.response = response;
    }
    public UserService getService() {
        return service;
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
}
