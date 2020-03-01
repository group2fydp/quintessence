package com.cove.login.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnauthorizedException extends RuntimeException {
    private ErrorResponse errorResponse;

    public UnauthorizedException(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setResponseStatus(HttpStatus.UNAUTHORIZED);
    }
}
