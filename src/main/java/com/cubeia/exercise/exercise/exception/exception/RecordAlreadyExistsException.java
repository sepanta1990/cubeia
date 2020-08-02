package com.cubeia.exercise.exercise.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class RecordAlreadyExistsException extends RuntimeException {
    public RecordAlreadyExistsException(String exception) {
        super(exception);
    }
}
