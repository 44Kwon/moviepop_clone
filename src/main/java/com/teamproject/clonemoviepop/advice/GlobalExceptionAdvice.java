package com.teamproject.clonemoviepop.advice;

import com.teamproject.clonemoviepop.exception.BusinessLogicException;
import com.teamproject.clonemoviepop.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = ErrorResponse.of(exception.getBindingResult());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorResponse errorResponse = ErrorResponse.of(exception.getConstraintViolations());
        return errorResponse;
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException exception) {
        final ErrorResponse errorResponse = ErrorResponse.of(exception.getExceptionCode());
        return new ResponseEntity(errorResponse, HttpStatus.valueOf(exception.getExceptionCode().getStatus()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        final ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        final ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, "Required request body is missing");
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        final ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, exception.getMessage());
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleFileSizeLimitExceededException(FileSizeLimitExceededException exception) {
        final ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, "이미지는 최대 5MB까지 업로드 가능합니다.");
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleSizeLimitExceededException(SizeLimitExceededException exception) {
        final ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, "전체 요청 크기가 최대 10MB를 초과하였습니다.");
        return errorResponse;
    }

}
