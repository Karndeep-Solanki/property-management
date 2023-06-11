package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register") //@Valid is responsible to kick error messages inside UserDTO,e.g.,@Notnull/@NotEmpty before sqlexception interrupt application.
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO){

        UserDTO userDTO1 = userService.register(userDTO);

        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO1, HttpStatus.CREATED);

        return responseEntity;
    }
    @PostMapping(path="/login",consumes = {"application/json"},produces={"application/json"})
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO){

        UserDTO userDTO1 = userService.login(userDTO.getOwnerEmail(),userDTO.getPassword());

     return new ResponseEntity<>(userDTO1, HttpStatus.OK);


    }

}
