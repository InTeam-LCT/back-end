package com.inteam.hakaton.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}
