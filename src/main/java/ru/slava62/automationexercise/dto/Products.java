package ru.slava62.automationexercise.dto;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;


@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"responseCode",
"products"
})
@Generated("jsonschema2pojo")
public class Products {

@JsonProperty("responseCode")
private Integer responseCode;
@JsonProperty("products")
private List<Product> products = new ArrayList<Product>();

}
