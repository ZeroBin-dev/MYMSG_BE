package com.myapi.mymsgapi.comm.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // ENUM 클래서 한글사용
public enum ExceptType {

  /******************/
  /** API Exception */
  /******************/
  RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
  ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),

  /******************/
  /** BIZ Exception */
  /******************/

  // 로그인
  LGIN001(HttpStatus.UNAUTHORIZED, "LGIN001", "로그인이 필요합니다."),

  // 회원가입
  JOIN001(HttpStatus.UNAUTHORIZED, "JOIN001", "회원가입에 실패하였습니다."),
  JOIN002(HttpStatus.UNAUTHORIZED, "JOIN002", "비밀번호가 일치 하지 않습니다."),
  JOIN003(HttpStatus.UNAUTHORIZED, "JOIN003", "유효한 형태의 ID 또는 이름이 아닙니다."),
  JOIN004(HttpStatus.UNAUTHORIZED, "JOIN004", "중복된 ID 입니다."),

  // 세션
  SESS001(HttpStatus.UNAUTHORIZED, "SESS001", "세션 타입이 일치하지 않습니다.");

  private final HttpStatus status;
  private final String code;
  private String message;

  ExceptType(HttpStatus status, String code) {
    this.status = status;
    this.code = code;
  }

  ExceptType(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}
