package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO){

        PropertyEntity pe = new PropertyEntity();

        pe.setTitle(propertyDTO.getTitle());
        pe.setAddress(propertyDTO.getAddress());
        //pe.setOwnerEmail(propertyDTO.getOwnerEmail());
        //pe.setOwnerName(propertyDTO.getOwnerName());
        pe.setPrice(propertyDTO.getPrice());
        pe.setDescription(propertyDTO.getDescription());

        return pe;
    }

    public PropertyDTO convertEntitytoDTO (PropertyEntity propertyEntity){

        PropertyDTO pd = new PropertyDTO();

        pd.setId(propertyEntity.getId());
        pd.setTitle(propertyEntity.getTitle());
        pd.setAddress(propertyEntity.getAddress());
        //pd.setOwnerEmail(propertyEntity.getOwnerEmail());
        //pd.setOwnerName(pd.getOwnerName());
        pd.setPrice(propertyEntity.getPrice());
        pd.setDescription(propertyEntity.getDescription());

        return pd;
    }
}
