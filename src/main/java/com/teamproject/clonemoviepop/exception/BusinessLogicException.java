package com.teamproject.clonemoviepop.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {  //언체크예외

    private final ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
