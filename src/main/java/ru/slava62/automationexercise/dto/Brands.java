package ru.slava62.automationexercise.dto;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Brands {

@JsonProperty("responseCode")
private Integer responseCode;
@JsonProperty("brands")
private List<Brand> brands = new ArrayList<Brand>();

}