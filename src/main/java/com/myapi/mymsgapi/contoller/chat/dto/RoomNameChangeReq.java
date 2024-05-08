package com.myapi.mymsgapi.contoller.chat.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomNameChangeReq {

  @NotEmpty
  private String roomId;

  @NotEmpty
  private String roomName;
}
