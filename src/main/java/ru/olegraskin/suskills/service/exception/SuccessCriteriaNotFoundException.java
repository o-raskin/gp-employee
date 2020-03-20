package ru.olegraskin.suskills.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SuccessCriteriaNotFoundException extends RuntimeException {

    public SuccessCriteriaNotFoundException(Long id) {
        super("Not found success criteria with id: " + id);
    }
}
