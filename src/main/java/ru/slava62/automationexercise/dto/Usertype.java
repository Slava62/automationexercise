package ru.slava62.automationexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usertype {
@JsonProperty("usertype")
private String usertype;
}

