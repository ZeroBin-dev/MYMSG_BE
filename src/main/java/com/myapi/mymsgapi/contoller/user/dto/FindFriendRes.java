package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindFriendRes extends BaseResponse {
  private String existYn;
  private String userId;
}
