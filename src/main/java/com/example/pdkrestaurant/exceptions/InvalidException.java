package com.example.pdkrestaurant.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.RuntimeErrorException;
import java.nio.file.InvalidPathException;
import java.util.concurrent.ExecutionException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
