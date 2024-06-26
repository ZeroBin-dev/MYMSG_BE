package com.myapi.mymsgapi.contoller.chat.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatUserJoinReq {

  @NotEmpty
  private List<String> userIdList;
  private String roomName;
}
