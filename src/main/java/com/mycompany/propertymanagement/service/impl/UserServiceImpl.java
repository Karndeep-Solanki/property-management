package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BussinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        UserDTO dto=null;
        if(optUe.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();

            // First Error Scenario
            ErrorModel errorModel = new ErrorModel();

            errorModel.setCode("Email_Already_Exist");
            errorModel.setErrorMessage("User already Register with this email");

            errorModelList.add(errorModel);


            throw new BussinessException(errorModelList);

        }else{
            UserEntity userEntity = userConverter.ConvertDTOtoEntity(userDTO);

            UserEntity userEntity1 = userRepository.save(userEntity);// problem: this will allow to make multiple account on same Email

            dto = userConverter.ConvertEntitytoDTO(userEntity1);
        }
        return dto;
    }

    @Override
    public UserDTO login(String email, String password) {

        UserDTO userDTO= null; // Reson of declaring null variable here is to non-make it local(if we declare inside method it will be local)

        Optional<UserEntity> optonalUserEntitybyOwnerEmailAndPassword = userRepository.findByOwnerEmailAndPassword(email, password);

        if(optonalUserEntitybyOwnerEmailAndPassword.isPresent()){
             userDTO = userConverter.ConvertEntitytoDTO(optonalUserEntitybyOwnerEmailAndPassword.get());
        }else {
            List<ErrorModel> errorModelList = new ArrayList<>();

            // First Error Scenario
            ErrorModel errorModel = new ErrorModel();

            errorModel.setCode("INVALID_LOGIN");
            errorModel.setErrorMessage("Incorrect Email or Password");

            errorModelList.add(errorModel);


            throw new BussinessException(errorModelList);
        }

        return userDTO;
    }
}
