package com.myapi.mymsgapi.comm.exception;

import com.myapi.mymsgapi.comm.types.ExceptType;
import lombok.Getter;

@Getter
public class YbBizException extends RuntimeException {
  private ExceptType error;

  public YbBizException(ExceptType e) {
    super(e.getMessage());
    this.error = e;
  }
}
