package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to throw when a particular entity is not found
 * 
 * @author jeraldfdo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends Exception {

    static final long serialVersionUID = -3387516993334229948L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
