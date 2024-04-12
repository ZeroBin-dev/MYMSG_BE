package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddFriendReq extends BaseRequest {
  private String userId;
  private String friendId;
}
