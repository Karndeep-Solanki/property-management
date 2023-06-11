package com.mycompany.propertymanagement.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@Getter
//@Setter
@Data // Data annotation is combination of @Getter and @Getter
@NoArgsConstructor
public class BussinessException extends RuntimeException{ //runtimeException is for unchecked exception

    private List<ErrorModel> errors;

    //public BussinessException(){} // NoArgs Constructor

    public BussinessException(List<ErrorModel> errors){ // withArgs Constructor
        this.errors=errors;
    }

}
