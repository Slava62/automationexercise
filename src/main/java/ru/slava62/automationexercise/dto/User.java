package ru.slava62.automationexercise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class User {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("title")
    private String title;
    @JsonProperty("birth_day")
    private String birthDay;
    @JsonProperty("birth_month")
    private String birthMonth;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("company")
    private String company;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("country")
    private String country;
    @JsonProperty("state")
    private String state;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zipcode")
    private String zipcode;
    @JsonProperty("mobile_number")
    private String mobile_number;
}
