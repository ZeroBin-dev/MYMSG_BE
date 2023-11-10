package com.myapi.mymsgapi.contoller.redis.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisGetResponse extends BaseResponse {
  private String value;
}
