package com.example.__2_IDLE.global.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
  RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
  ;

  private final HttpStatus httpStatus;
  private final String message;
}