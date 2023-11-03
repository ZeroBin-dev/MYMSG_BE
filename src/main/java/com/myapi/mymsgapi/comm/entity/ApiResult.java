package com.myapi.mymsgapi.comm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ApiResult {
  private String status;
  private String message;
  private ApiExceptionEntity exception;
  private ApiResponseEntity result;

  @Builder
  public ApiResult(String status, String message, ApiExceptionEntity exception) {
    this.status = status;
    this.message = message;
    this.exception = exception;
  }

  @Builder
  public ApiResult(String status, String message, ApiResponseEntity result) {
    this.status = status;
    this.message = message;
    this.result = result;
  }

}
