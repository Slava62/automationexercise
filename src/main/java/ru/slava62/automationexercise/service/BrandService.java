package ru.slava62.automationexercise.service;

// import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.slava62.automationexercise.dto.*;

public interface BrandService {

    @GET("brandsList")
    Call<Brands> getBrandList();

    @PUT("brandsList")
    Call<MessageJSON> putBrandList();
}
