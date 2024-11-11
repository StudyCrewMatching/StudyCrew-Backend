package com.studycrew.studycrew_backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않습니다."),
    NOT_VALID(HttpStatus.BAD_REQUEST, "유효하지 않은 값입니다."),
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, "위반된 인수"),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "제약 조건 위반");

    private final HttpStatus httpStatus;
    private final String message;
}
