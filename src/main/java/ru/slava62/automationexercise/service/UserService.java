package ru.slava62.automationexercise.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.slava62.automationexercise.dto.MessageJSON;
import ru.slava62.automationexercise.dto.User;

public interface UserService {
    @FormUrlEncoded
    @POST("createAccount")
    Call<MessageJSON> createUser(@Field("name")  String name,
                                 @Field("email")  String email,
                                 @Field("password")  String password,
                                 @Field("title ")  String title ,
                                 @Field("birth_date")  int birthDate,
                                 @Field("birth_month")  String birthMonth,
                                 @Field("birth_year")  String birthYear,
                                 @Field("firstname")  String firstname,
                                 @Field("lastname")  String lastname,
                                 @Field("company")  String company,
                                 @Field("address1")  String address1,
                                 @Field("address2")  String address2,
                                 @Field("country")  String country,
                                 @Field("zipcode")  int zipcode,
                                 @Field("state")  String state,
                                 @Field("city")  String city,
                                 @Field("mobile_number")  String mobileNumber
                                 );
}
