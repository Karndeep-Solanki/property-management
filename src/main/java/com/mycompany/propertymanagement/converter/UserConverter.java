package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity ConvertDTOtoEntity (UserDTO userDTO){

        UserEntity ud = new UserEntity();

        ud.setOwnerEmail(userDTO.getOwnerEmail());
        ud.setOwnerName(userDTO.getOwnerName());
        ud.setPassword(userDTO.getPassword());
        ud.setPhone(userDTO.getPhone());
        return ud;
    }

    public UserDTO ConvertEntitytoDTO (UserEntity userEntity){

        UserDTO ue = new UserDTO();

        ue.setId(userEntity.getId());
        ue.setOwnerEmail(userEntity.getOwnerEmail());
        ue.setOwnerName(userEntity.getOwnerName());
        //ue.setPassword(userEntity.getPassword());
        ue.setPhone(userEntity.getPhone());
        return ue;
    }
}
