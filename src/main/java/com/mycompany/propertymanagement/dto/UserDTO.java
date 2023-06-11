package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)// only field which are not null will be send back into response
@JsonIgnoreProperties(ignoreUnknown = true)// unknown fields willl be ignored
public class UserDTO {

    private Long id;

    private String ownerName;
    private String ownerEmail;
    private String phone;
    private String password;

}
