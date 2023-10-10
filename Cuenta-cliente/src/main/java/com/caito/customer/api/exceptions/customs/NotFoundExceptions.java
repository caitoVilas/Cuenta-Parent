package com.caito.customer.api.exceptions.customs;

/**
 * @author claudio.vilas
 * date 09/2023
 */

public class NotFoundExceptions extends RuntimeException{
    public NotFoundExceptions(String message) {
        super(message);
    }
}
