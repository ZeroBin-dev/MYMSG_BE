package com.myapi.mymsgapi.contoller.chat.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUserInfoReq {

  @NotEmpty
  private String userId;

  @NotEmpty
  private String roomId;
}
