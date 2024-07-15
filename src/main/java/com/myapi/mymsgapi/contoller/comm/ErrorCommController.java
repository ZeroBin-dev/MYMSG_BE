package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.comm.exception.ApiExceptionEntity;
import com.myapi.mymsgapi.comm.exception.ApiResult;
import com.myapi.mymsgapi.comm.exception.YbBizException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class ErrorCommController implements ErrorController {

  @RequestMapping("/error")
  public String handlerError(HttpServletRequest request, HttpServletResponse response, Model model) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    int statusCode = Integer.parseInt(status.toString());
    response.setStatus(statusCode);

    model.addAttribute("code", status.toString());
    model.addAttribute("msg", HttpStatus.valueOf(Integer.valueOf(status.toString())));

    return "error/error";
  }

}
