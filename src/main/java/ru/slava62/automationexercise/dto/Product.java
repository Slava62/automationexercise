package ru.slava62.automationexercise.dto;

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
public class Product {
@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("price")
private String price;
@JsonProperty("brand")
private String brand;
@JsonProperty("category")
private Category category;
}
