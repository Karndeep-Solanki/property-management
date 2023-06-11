package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BussinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;



    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(propertyDTO.getUserId());

        //PropertyDTO dto = null; (instead of creating new object of  PropertyDTO, use the existance as shown below, otherwise throws exception)

        if(optionalUserEntity.isPresent()) {

            PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optionalUserEntity.get());

            PropertyEntity propertyEntity = propertyRepository.save(pe);

             propertyDTO = propertyConverter.convertEntitytoDTO(propertyEntity);
        }else {
            List<ErrorModel> errorModelList = new ArrayList<>();

            // First Error Scenario
            ErrorModel errorModel = new ErrorModel();

            errorModel.setCode("USER_ID_Not_Exist");
            errorModel.setErrorMessage("User does not exist");

            errorModelList.add(errorModel);

            throw new BussinessException(errorModelList);
        }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        List<PropertyEntity> listOfProp = propertyRepository.findAll();

        List<PropertyDTO> proplist = new ArrayList<>();

        for (PropertyEntity pe : listOfProp){
            PropertyDTO dto =propertyConverter.convertEntitytoDTO(pe);
            proplist.add(dto);
        }

        return proplist;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listOfProp = propertyRepository.findAllByUserEntityId(userId);

        List<PropertyDTO> proplist = new ArrayList<>();

        for (PropertyEntity pe : listOfProp){
            PropertyDTO dto =propertyConverter.convertEntitytoDTO(pe);
            proplist.add(dto);
        }

        return proplist;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> OptionEN = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;
        if (OptionEN.isPresent()) {
            PropertyEntity pe = OptionEN.get();// Overriding data  fetched from DB


            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            //pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            //pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());

            PropertyEntity propertyEntity = propertyRepository.save(pe);

            dto = propertyConverter.convertEntitytoDTO(propertyEntity);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> OptionEN = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;
        if (OptionEN.isPresent()) {
            PropertyEntity pe = OptionEN.get();// Overriding data  fetched from DB

            pe.setPrice(propertyDTO.getPrice());

            PropertyEntity propertyEntity = propertyRepository.save(pe);//saving updated entity

            dto = propertyConverter.convertEntitytoDTO(propertyEntity);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> OptionEN = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;
        if (OptionEN.isPresent()) {
            PropertyEntity pe = OptionEN.get();// Overriding data  fetched from DB

            pe.setDescription(propertyDTO.getDescription());

            PropertyEntity propertyEntity = propertyRepository.save(pe);

            dto = propertyConverter.convertEntitytoDTO(propertyEntity);
        }
        return dto;



    }

    @Override
    public void deleteById(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }


}
