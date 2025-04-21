package com.kmu.anki.backend.domain.user.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class UserOptionRequiredException extends RuntimeException{

    public UserOptionRequiredException(String requiredOption) {
        super("user option required. required option : " + requiredOption);
    }

}
