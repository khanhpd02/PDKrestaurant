package com.example.pdkrestaurant.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.RuntimeErrorException;
import java.nio.file.InvalidPathException;
import java.util.concurrent.ExecutionException;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 3/15/23
 * Time      : 8:57 AM
 * Filename  : InvalidException
 */


public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
