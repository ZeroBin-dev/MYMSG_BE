package com.myapi.mymsgapi.contoller.redis.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisSetResponse extends BaseResponse {
  private String succYn;
}
