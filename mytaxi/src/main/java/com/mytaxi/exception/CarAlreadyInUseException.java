package com.mytaxi.exception;

/**
 * @author jeraldfdo
 */
public class CarAlreadyInUseException extends Exception
{

    public CarAlreadyInUseException(final String message)
    {
        super(message);
    }

}
