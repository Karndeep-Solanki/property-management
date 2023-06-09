package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorDTO {

    private Double num1;
    private Double num2;
    private Double num3;

    @JsonProperty("num41")//annotation to specify that json payload has different key name, it removes tight coupling of java variable with Json.
    private Double num4;


}
