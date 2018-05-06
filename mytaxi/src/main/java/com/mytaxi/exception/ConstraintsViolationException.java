package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to throw when certain constrains are violated
 * 
 * @author jeraldfdo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class ConstraintsViolationException extends Exception {

    static final long serialVersionUID = -3387516993224229948L;

    public ConstraintsViolationException(String message) {
        super(message);
    }

}
