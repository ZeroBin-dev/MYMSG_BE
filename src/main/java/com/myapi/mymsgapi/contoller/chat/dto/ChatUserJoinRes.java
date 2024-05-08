package com.myapi.mymsgapi.contoller.chat.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatUserJoinRes extends BaseResponse {
  private String roomId;
}
