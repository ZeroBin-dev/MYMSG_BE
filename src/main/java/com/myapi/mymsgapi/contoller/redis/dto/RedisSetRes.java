package com.myapi.mymsgapi.contoller.redis.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisSetRes extends BaseRes {
  private String succYn;
}
