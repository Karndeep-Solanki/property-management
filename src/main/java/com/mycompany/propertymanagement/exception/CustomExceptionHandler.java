package com.mycompany.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // by this annotation(we are telling spring that: trat this class as exception handler class) (now spring knows this is the class which spring have to look for a perticular  handler, when that perticular exception is thrown)
//e.g., Let's say if exception of Object a of your class a is thrown, then here it will come and check if there is any handler for that a or not.

public class CustomExceptionHandler { //this is Central class responsible to HANDLE (other class responsible to just throw exception)  ALL the  exception and need to  throw all at once - AOP()aspect oriented programming Concept

    private final Logger logger = LoggerFactory.getLogger(this.getClass());//Logger sould be  impoertwd from Slf4j library

    //@ExceptionHandler instruct: this is the method/function should call when BussinessException.class type of exception is thrown,(so now, BussinessException.class type of exception is thrown we want to spring to come here)
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBussinessException(BussinessException bex){
     for (ErrorModel em: bex.getErrors()){
         logger.info("Bussiness Exception is thrown - level-info:{} - {}",em.getCode(),em.getErrorMessage());
         logger.debug("Bussiness Exception is thrown - level-debug:{} - {}",em.getCode(),em.getErrorMessage());
         logger.warn("Bussiness Exception is thrown - level-warn:{} - {}",em.getCode(),em.getErrorMessage());
         logger.error("Bussiness Exception is thrown - level-error:{} - {}",em.getCode(),em.getErrorMessage());

     }

        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }


    //field validation exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)//spring framework will throw object of MethodArgumentNotValidException.class by ITSELF
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;

        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for(FieldError fe :fieldErrorList){
            logger.info("Inside Field validation:{} - {}",fe.getField(),fe.getDefaultMessage());
            logger.debug("Inside Field validation:{} - {}",fe.getField(),fe.getDefaultMessage());
            errorModel= new ErrorModel();

            errorModel.setCode(fe.getField());
            errorModel.setErrorMessage(fe.getDefaultMessage());

            errorModelList.add(errorModel);
        }

        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);

    }
}
