package com.mycompany.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice // by this annotation(we are telling spring that: trat this class as exception handler class) (now spring knows this is the class which spring have to look for a perticular  handler, when that perticular exception is thrown)
//e.g., Let's say if exception of Object a of your class a is thrown, then here it will come and check if there is any handler for that a or not.

public class CustomExceptionHandler { //this is Central class responsible to HANDLE (other class responsible to just throw exception)  ALL the  exception and need to  throw all at once - AOP()aspect oriented programming Concept


    //@ExceptionHandler instruct: this is the method/function should call when BussinessException.class type of exception is thrown,(so now, BussinessException.class type of exception is thrown we want to spring to come here)
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBussinessException(BussinessException bex){
        System.out.println("Bussiness Exception is thrown");
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
