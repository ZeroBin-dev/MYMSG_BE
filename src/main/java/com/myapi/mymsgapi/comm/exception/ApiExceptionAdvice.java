package com.myapi.mymsgapi.comm.exception;

import com.myapi.mymsgapi.comm.types.ExceptType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ApiExceptionAdvice {

  @ExceptionHandler({YbBizException.class})
  public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request, final YbBizException e) {

    ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
            .errorCode(e.getError().getCode())
            .errorMessage(e.getError().getMessage())
            .build();

    //e.printStackTrace();

    return ResponseEntity
            .status(e.getError().getStatus())
            .body(ApiResult.builder()
                    .status("error")
                    .message("오류")
                    .exception(apiExceptionEntity)
                    .build());
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request, final RuntimeException e) {

    ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
            .errorCode(ExceptType.RUNTIME_EXCEPTION.getCode())
            .errorMessage(e.getMessage())
            .build();

    e.printStackTrace();

    return ResponseEntity
            .status(ExceptType.RUNTIME_EXCEPTION.getStatus())
            .body(ApiResult.builder()
                    .status("error")
                    .message("오류")
                    .exception(apiExceptionEntity)
                    .build());
  }

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {

    ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
            .errorCode(ExceptType.ACCESS_DENIED_EXCEPTION.getCode())
            .errorMessage(e.getMessage())
            .build();

    e.printStackTrace();

    return ResponseEntity
            .status(ExceptType.ACCESS_DENIED_EXCEPTION.getStatus())
            .body(ApiResult.builder()
                    .status("error")
                    .message("오류")
                    .exception(apiExceptionEntity)
                    .build());
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request, final Exception e) {

    ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
            .errorCode(ExceptType.INTERNAL_SERVER_ERROR.getCode())
            .errorMessage(e.getMessage())
            .build();

    e.printStackTrace();

    return ResponseEntity
            .status(ExceptType.INTERNAL_SERVER_ERROR.getStatus())
            .body(ApiResult.builder()
                    .status("error")
                    .message("오류")
                    .exception(apiExceptionEntity)
                    .build());
  }

}
