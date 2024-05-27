package com.myapi.test;

import com.google.gson.Gson;
import com.myapi.mymsgapi.MymsgApiApplication;
import com.myapi.mymsgapi.comm.utils.DateUtil;
import com.myapi.mymsgapi.comm.utils.StringUtil;
import com.myapi.mymsgapi.contoller.user.dto.UserLginReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest(classes = MymsgApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ApplicationTester {

  @Autowired
  private Gson gson;

  @Autowired
  private MockMvc mockMvc;

  /**
   * 로그인 성공
   */
  @Test
  void 로그인성공() throws Exception {
    final String url = "/user/loginProc";

    final UserLginReq userLginReq = new UserLginReq();
    userLginReq.setUserId("admin");
    userLginReq.setUserPw("1234");

    final ResultActions result = mockMvc.perform(
      MockMvcRequestBuilders.post(url)
        .content(gson.toJson(userLginReq))
        .contentType(MediaType.APPLICATION_JSON)
    );

    int status = result.andReturn().getResponse().getStatus();
    assert status == 200;
  }


  /**
   * 로그인 실패
   */
  @Test
  void 로그인실패_유효성오류() throws Exception {
    final String url = "/user/loginProc";

    final UserLginReq userLginReq = new UserLginReq();
    userLginReq.setUserId("admin");
    userLginReq.setUserPw("");

    final ResultActions result = mockMvc.perform(
      MockMvcRequestBuilders.post(url)
        .content(gson.toJson(userLginReq))
        .contentType(MediaType.APPLICATION_JSON)
    );

    int status = result.andReturn().getResponse().getStatus();
    assert status == 500;
  }


  /**
   * 간단한 Util 테스트
   */
  @Test
  public void 날짜테스트() {
    String str = DateUtil.getDateStr();
    if (StringUtil.isNotEmpty(str)) {
      System.out.println(str);
    } else {
      System.out.println("isEmpty");
    }
  }
}
