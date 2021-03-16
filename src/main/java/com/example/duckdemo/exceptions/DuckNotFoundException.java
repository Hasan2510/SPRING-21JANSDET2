package com.example.duckdemo.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Duck wasn't found")
public class DuckNotFoundException extends EntityNotFoundException {

    /**
     *
     */
    private static final long serialVersionUID = 2075886427848921151L;

    public DuckNotFoundException() {
        super();
    }

    public DuckNotFoundException(String message) {
        super(message);
    }

}
