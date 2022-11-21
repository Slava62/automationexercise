package ru.slava62.automationexercise.service;

// import okhttp3.ResponseBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.slava62.automationexercise.dto.*;

public interface BrandService {

    @GET("brandsList")
    Call<ResponseBody> getBrandList();

    @PUT("brandsList")
    Call<ResponseBody> putBrandList();
}
