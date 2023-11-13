package com.myapi.mymsgapi.comm.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// TODO : 메시지 필터 수정 예정 ㅠㅠ
// @Component
public class MessageFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

    chain.doFilter(requestWrapper, responseWrapper);

    byte[] responseArray = responseWrapper.getContentAsByteArray();
    String responseStr = new String(responseArray, "UTF-8");

    JsonNode node = new ObjectMapper().readTree(responseStr);

    // TODO : 메세지 정의
    String status = node.path("status").asText();
    if("error".equals(status)){

    } else {
      ((ObjectNode)node).put("status", "success");
      ((ObjectNode)node).put("message", "성공");
      //((ObjectNode)node).put("result", node);
    }

    String newResponse = new ObjectMapper().writeValueAsString(node);
    response.setContentType("application/json");
    response.setContentLength(newResponse.length());
    response.getOutputStream().write(newResponse.getBytes());
  }

  private static Map<String, Object> setRequestMap(final ContentCachingRequestWrapper request){
    Map<String, Object> requestMap = new HashMap<>();
    return requestMap;
  }


}
