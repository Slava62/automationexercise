package ru.slava62.automationexercise.service;

// import okhttp3.ResponseBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.slava62.automationexercise.dto.*;

public interface ProductService {
    
    @FormUrlEncoded
    @POST("searchProduct")
    Call<ResponseBody> searchProduct(@Field("search_product") String product);

    @POST("searchProduct")
    Call<ResponseBody> searchProduct();

    @GET("productsList")
    Call<ResponseBody> getProductList();

    @POST("productsList")
    Call<ResponseBody> postProductList();
}
