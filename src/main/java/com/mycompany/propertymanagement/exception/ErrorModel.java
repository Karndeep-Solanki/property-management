package com.mycompany.propertymanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorModel {

    private String code;
    private String errorMessage;
}
