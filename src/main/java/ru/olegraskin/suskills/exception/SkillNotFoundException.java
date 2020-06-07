package ru.olegraskin.suskills.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SkillNotFoundException extends RuntimeException {

    public SkillNotFoundException(Long id) {
        super("Not found skill with id: " + id);
    }
}
