package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookmarkReq extends BaseRequest {
  private String userId;
  private String friendId;
  private String bookmarkOnOff; // Y:등록,N:해제
}
