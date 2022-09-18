package ru.slava62.automationexercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import com.fasterxml.jackson.annotation.JsonInclude;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
@JsonProperty("usertype")
private Usertype usertype;
@JsonProperty("category")
private String category;
}
