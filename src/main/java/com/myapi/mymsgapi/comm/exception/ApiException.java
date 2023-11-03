package com.myapi.mymsgapi.comm.exception;

import com.myapi.mymsgapi.comm.types.ExceptType;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  private ExceptType error;

  public ApiException(ExceptType e) {
    super(e.getMessage());
    this.error = e;
  }
}
