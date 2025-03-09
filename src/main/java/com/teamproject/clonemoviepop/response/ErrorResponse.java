package com.teamproject.clonemoviepop.response;

import com.teamproject.clonemoviepop.exception.ExceptionCode;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 오류 Response
 */
@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(HttpStatus status) {
        return new ErrorResponse(status.value(), status.getReasonPhrase());
    }

    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(status.value(), message);
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult),null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(),exceptionCode.getMessage());
    }

    //FieldError는 필드 유효성 검증에서 발생한 오류에 대한 정보를 저장
    @Getter
    public static class FieldError {
        private String field; //오류가 발생한 필드의 이름
        private Object rejectedValue; //해당 필드에 대해 잘못된 값
        private String reason; //오류에 대한 설명 메시지

        public FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        //BindingResult에서 발생한 필드 오류들을 FieldError 객체로 변환하여 리스트로 반환합니다.
        public static List<FieldError> of(BindingResult bindingResult) {
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue(),
                            error.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
        }
    }

    //제약 조건 위반 오류에 대한 정보를 저장합니다.
    //이는 주로 JSR-303/JSR-380 표준(예: @NotNull, @Size 등) 어노테이션을 사용할 때 발생합니다.
    @Getter
    public static class ConstraintViolationError {
        private String propertyPath; //오류가 발생한 객체의 속성 경로
        private Object rejectedValue; // 잘못된 값
        private String reason; //오류에 대한 설명 메시지

        public ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        //제약 위반 오류들을 ConstraintViolationError 객체로 변환하여 리스트로 반환합니다.
        //Set<ConstraintViolation<?>>**에서 오류를 추출
        public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(error -> new ConstraintViolationError(
                            error.getPropertyPath().toString(),
                            error.getInvalidValue(),
                            error.getMessage()
                    ))
                    .collect(Collectors.toList());
        }
    }
}

