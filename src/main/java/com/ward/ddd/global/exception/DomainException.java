package com.ward.ddd.global.exception;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final int code;
    private final String message;

    public DomainException(int code, String message) {
        super(code + ": " + message);
        this.code = code;
        this.message = message;
    }
}
