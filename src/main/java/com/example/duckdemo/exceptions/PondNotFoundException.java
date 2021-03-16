package com.example.duckdemo.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Duck wasn't found")
public class PondNotFoundException extends EntityNotFoundException {

    /**
     *
     */
    private static final long serialVersionUID = 3991888817140673535L;

    public PondNotFoundException() {
        super();
    }

    public PondNotFoundException(String message) {
        super(message);
    }

}
