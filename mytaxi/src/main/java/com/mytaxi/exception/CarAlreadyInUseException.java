package com.mytaxi.exception;

/**
 * Exception class to throw when the Car is already reserved
 * 
 * @author jeraldfdo
 */
public class CarAlreadyInUseException extends Exception {

    public CarAlreadyInUseException(final String message) {
        super(message);
    }

}
