package ru.slava62.automationexercise.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface UserService {
    @FormUrlEncoded
    @POST("createAccount")
    Call<ResponseBody> createUser(@Field("name")  String name,
                                 @Field("email")  String email,
                                 @Field("password")  String password,
                                 @Field("title ")  String title ,
                                 @Field("birth_date")  String birthDate,
                                 @Field("birth_month")  String birthMonth,
                                 @Field("birth_year")  String birthYear,
                                 @Field("firstname")  String firstname,
                                 @Field("lastname")  String lastname,
                                 @Field("company")  String company,
                                 @Field("address1")  String address1,
                                 @Field("address2")  String address2,
                                 @Field("country")  String country,
                                 @Field("zipcode")  String zipcode,
                                 @Field("state")  String state,
                                 @Field("city")  String city,
                                 @Field("mobile_number")  String mobileNumber
                                 );
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteAccount", hasBody = true)
    Call<ResponseBody> deleteUserAccount(@Field("email") String email,
                                        @Field("password") String password);
    @FormUrlEncoded
    @HTTP(method = "POST", path = "verifyLogin", hasBody = true)
    Call<ResponseBody> loginUser(@Field("email") String email,
                                @Field("password") String password);
    @FormUrlEncoded
    @HTTP(method = "POST", path = "verifyLogin", hasBody = true)
    Call<ResponseBody> loginUser(@Field("password") String password);

    @DELETE("verifyLogin")
    Call<ResponseBody> deleteMethod();

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "updateAccount", hasBody = true)
    Call<ResponseBody> updateUser(@Field("name")  String name,
                                 @Field("email")  String email,
                                 @Field("password")  String password,
                                 @Field("title ")  String title ,
                                 @Field("birth_date")  String birthDate,
                                 @Field("birth_month")  String birthMonth,
                                 @Field("birth_year")  String birthYear,
                                 @Field("firstname")  String firstname,
                                 @Field("lastname")  String lastname,
                                 @Field("company")  String company,
                                 @Field("address1")  String address1,
                                 @Field("address2")  String address2,
                                 @Field("country")  String country,
                                 @Field("zipcode")  String zipcode,
                                 @Field("state")  String state,
                                 @Field("city")  String city,
                                 @Field("mobile_number")  String mobileNumber
    );
    @GET("getUserDetailByEmail")
    Call<ResponseBody> getDetail(@Query("email") String email);
}