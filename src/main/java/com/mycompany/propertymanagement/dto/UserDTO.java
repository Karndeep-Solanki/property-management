package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)// only field which are not null will be send back into response
@JsonIgnoreProperties(ignoreUnknown = true)// unknown fields willl be ignored
public class UserDTO {

    private Long id;

    private String ownerName;

    @NotNull(message = "Owner Email is mandatory")
    @NotEmpty(message = "OwnerEmail cannot be empty")
    @Size(min = 1,max = 50, message = "message owner email should be between 1 to 50 characters in length")
    private String ownerEmail;

    private String phone;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String street;
    private String houseNo;
    private String city;
    private String postalCode;
    private String country;

}
