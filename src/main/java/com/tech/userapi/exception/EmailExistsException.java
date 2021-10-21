package com.tech.userapi.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException() {
        super("El correo ya esta registrado");
    }
}
