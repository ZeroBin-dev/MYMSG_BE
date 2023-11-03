package com.myapi.mymsgapi.comm.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@Getter
public class MyBizException extends RuntimeException{

  private HttpStatus httpStatus;
  private String code;
  private String message;

  // TODO : 메시지 프로퍼티 연결 필요
  public MyBizException(String message) {
    super(message);
    System.out.println("errorMsg : "+message);
  }

}
