package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RESTful API is just mapping of a url to a java class function.

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${pms.dummy}")//can throw error if properties are not present
    private String dummy;

    @Value("${spring.datasource.url:}")//adding ":" prevents any Error/null which can interrupt application
    private String dbUrl;


    @Autowired
    private PropertyService propertyService;

    //http://localhost:8080/api/v1/properties/hello
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){

        PropertyDTO dto = propertyService.saveProperty(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){

        //System.out.println(dummy);
        //System.out.println(dbUrl);

        List<PropertyDTO> allProperties = propertyService.getAllProperties();

        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(allProperties,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesforUser(@PathVariable("userId")Long userId){

        List<PropertyDTO> allProperties = propertyService.getAllPropertiesForUser(userId);

        return new ResponseEntity<>(allProperties,HttpStatus.OK);

    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO dto = propertyService.updateProperty(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);

        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO dto = propertyService.updatePropertyDescription(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);

        return responseEntity;
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO dto = propertyService.updatePropertyPrice(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("properties/delete/{propertyId}")
    public ResponseEntity deleteById(@PathVariable Long propertyId){
        propertyService.deleteById(propertyId);

        ResponseEntity responseEntity = new ResponseEntity<>("Successfully Deleted Id:"+propertyId, HttpStatus.NO_CONTENT);

        return responseEntity;

    }
}
