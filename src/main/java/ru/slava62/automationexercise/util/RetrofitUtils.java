package ru.slava62.automationexercise.util;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.slava62.automationexercise.dto.*;
import ru.slava62.automationexercise.service.*;


// import ru.slava62.service.ProductService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

@UtilityClass
public class RetrofitUtils {
   HttpLoggingInterceptor logging =  new HttpLoggingInterceptor(new PrettyLogger());

    public Retrofit getRetrofit() throws MalformedURLException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMinutes(1l))
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build(); 

        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }

    public ProductService getProductService() throws MalformedURLException{
            return getRetrofit().create(ProductService.class);
        }
    
    public BrandService getBrandService() throws MalformedURLException{
            return getRetrofit().create(BrandService.class);
        }

     public UserService getUserService() throws MalformedURLException{
        return getRetrofit().create(UserService.class);
     }

    public Response<ResponseBody> createUser(User user, UserService service) throws IOException{
        return service
                .createUser(user.getName(), user.getEmail(), user.getPassword(),
                        user.getTitle(), user.getBirthDay(), user.getBirthMonth(),
                        user.getBirthYear(), user.getFirstName(), user.getLastName(),
                        user.getCompany(), user.getAddress1(), user.getAddress2(),
                        user.getCountry(), user.getZipcode(), user.getState(), user.getCity(),
                        user.getMobile_number()
                        )
                .execute();
    }

    public Response<ResponseBody> postProductSearch(String product, ProductService service) throws IOException {
        return service
                .searchProduct(product)
                .execute();
    }

    public Response<ResponseBody> postProductSearchNoParameter(ProductService service) throws IOException {
            return service
                    .searchProduct()
                    .execute();
    }

    public Response<ResponseBody> getProductList(ProductService service) throws IOException {
        return service
                .getProductList()
                .execute();
    }

    public Response<ResponseBody> postProductList(ProductService service) throws IOException {
        return service
                .postProductList()
                .execute();
    }

    public Response<ResponseBody> getBrandList(BrandService service) throws IOException {
        return service
                .getBrandList()
                .execute();
    }

    public Response<ResponseBody> putBrandList(BrandService service) throws IOException {
        return service
                .putBrandList()
                .execute();
    }

    public Response<ResponseBody> deleteUser(User testUser, UserService service) throws IOException {
        return service
                .deleteUserAccount(testUser.getEmail(),testUser.getPassword())
                .execute();
    }

    public static Response<ResponseBody> loginUser(User testUser, UserService service) throws IOException{
        return service
                .loginUser(testUser.getEmail(),testUser.getPassword())
                .execute();
    }
    public static Response<ResponseBody> loginUser(String password, UserService service) throws IOException{
        return service
                .loginUser(password)
                .execute();
    }

    public static Response<ResponseBody> deleteMethod(UserService service) throws IOException {
        return service
                .deleteMethod()
                .execute();
    }

    public static Response<ResponseBody> updateUser(User user, UserService service) throws IOException{
        return service
                .updateUser(user.getName(), user.getEmail(), user.getPassword(),
                        user.getTitle(), user.getBirthDay(), user.getBirthMonth(),
                        user.getBirthYear(), user.getFirstName(), user.getLastName(),
                        user.getCompany(), user.getAddress1(), user.getAddress2(),
                        user.getCountry(), user.getZipcode(), user.getState(), user.getCity(),
                        user.getMobile_number()
                )
                .execute();
    }

    public static Response<ResponseBody> getUserDetails(User testUser, UserService service) throws IOException{
        return service
                .getDetail(testUser.getEmail())
                .execute();
    }
}
