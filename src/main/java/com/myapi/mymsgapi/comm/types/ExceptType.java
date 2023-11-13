package com.myapi.mymsgapi.comm.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // ENUM 클래스 한글사용
public enum ExceptType {

  /******************/
  /** API Exception */
  /******************/
  RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "런타임 오류가 발생하였습니다."),
  ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002", "권한이 없습니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003", "서버 통신중 오류가 발생하였습니다."),

  /******************/
  /** BIZ Exception */
  /******************/

  // 로그인
  LGIN001(HttpStatus.UNAUTHORIZED, "LGIN001", "로그인이 필요합니다."),
  LGIN002(HttpStatus.UNAUTHORIZED, "LGIN002", "아이디 또는 비밀번호를 잘못 입력하였습니다."),
  LGIN003(HttpStatus.UNAUTHORIZED, "LGIN003", "비밀번호 5회 이상 잘못 입력하였습니다."),

  // 회원가입
  JOIN001(HttpStatus.UNAUTHORIZED, "JOIN001", "회원가입에 실패하였습니다."),
  JOIN002(HttpStatus.UNAUTHORIZED, "JOIN002", "비밀번호가 일치 하지 않습니다."),
  JOIN003(HttpStatus.UNAUTHORIZED, "JOIN003", "유효한 형태의 ID 또는 이름이 아닙니다."),
  JOIN004(HttpStatus.UNAUTHORIZED, "JOIN004", "중복된 ID 입니다."),

  // 세션
  SESS001(HttpStatus.UNAUTHORIZED, "SESS001", "세션 타입이 일치하지 않습니다.");

  // 컬럼 정의
  private final HttpStatus status;
  private final String code;
  private final String message;

}
