package com.example.duckdemo.exceptions;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "What is a duck's favorite game? Beakaboo. Cos' this one's missing")
public class FunnyDuckNotFoundException extends EntityNotFoundException {

    /**
     *
     */
    private static final long serialVersionUID = -8584328772507161589L;

}
