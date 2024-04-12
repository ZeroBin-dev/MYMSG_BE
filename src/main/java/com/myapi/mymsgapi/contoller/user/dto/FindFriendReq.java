package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindFriendReq extends BaseRequest {
  private String userId;
}
