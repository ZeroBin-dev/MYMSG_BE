package com.myapi.mymsgapi.contoller.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatUserJoinReq {
  private List<String> userIdList;
  private String roomName;
}
