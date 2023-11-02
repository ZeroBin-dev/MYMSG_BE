package com.myapi.mymsgapi.contoller.redis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapi.mymsgapi.contoller.comm.dto.BaseReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedisGetReq extends BaseReq {
  @NotEmpty
  private String key;
}
