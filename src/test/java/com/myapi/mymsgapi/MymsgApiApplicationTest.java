package com.myapi.mymsgapi;

import com.google.gson.Gson;
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MymsgApiApplicationTest {

  @Autowired
  private Gson gson;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void 로그인호출성공() throws Exception {
    // given
    final String url = "/user/loginProc";

    final UserLginReq userLginReq = new UserLginReq();
    userLginReq.setUserId("admin");
    userLginReq.setUserPw("1234");

    // when
    final ResultActions result = mockMvc.perform(
      MockMvcRequestBuilders.post(url)
        .content(new Gson().toJson(userLginReq))
        .contentType(MediaType.APPLICATION_JSON)
    );

    // then
    int status = result.andReturn().getResponse().getStatus();
    assert status == 200;
  }

  @Test
  void 로그인호출실패_유효성에러() throws Exception {
    // given
    final String url = "/user/loginProc";

    final UserLginReq userLginReq = new UserLginReq();
    userLginReq.setUserId("admin");
    userLginReq.setUserPw("");

    // when
    final ResultActions result = mockMvc.perform(
      MockMvcRequestBuilders.post(url)
        .content(new Gson().toJson(userLginReq))
        .contentType(MediaType.APPLICATION_JSON)
    );

    // then
    int status = result.andReturn().getResponse().getStatus();
    assert status == 500;
  }

}