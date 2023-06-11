package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {

    // By Default anything inside interface is public & Final

    UserDTO register (UserDTO userDTO);
    UserDTO login (String email, String password);
}
