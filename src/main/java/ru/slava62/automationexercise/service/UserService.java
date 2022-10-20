package ru.slava62.automationexercise.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.slava62.automationexercise.dto.MessageJSON;

public interface UserService {
    @FormUrlEncoded
    @POST("createAccount")
    Call<MessageJSON> createUser(@Field("name")  String name,
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
    Call<MessageJSON> deleteUserAccount(@Field("email") String email,
                                         @Field("password") String password);
}
//https://automationexercise.com/api/deleteAccount/?email=56e2f51b@mailto.plus&password=password