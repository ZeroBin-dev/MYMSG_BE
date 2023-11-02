package com.myapi.mymsgapi.contoller.redis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapi.mymsgapi.contoller.comm.dto.BaseReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedisSetReq extends BaseReq {
  @NotEmpty
  private String key;
  private String value;
}
