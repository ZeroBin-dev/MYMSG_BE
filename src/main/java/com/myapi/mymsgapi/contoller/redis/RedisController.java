package com.myapi.mymsgapi.contoller.redis;

import com.myapi.mymsgapi.contoller.comm.dto.CnstReq;
import com.myapi.mymsgapi.contoller.comm.dto.CnstRes;
import com.myapi.mymsgapi.contoller.redis.dto.RedisGetReq;
import com.myapi.mymsgapi.contoller.redis.dto.RedisGetRes;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetReq;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetRes;
import com.myapi.mymsgapi.service.redis.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/redis")
public class RedisController {

  private final RedisService _redisService;

  @ResponseBody
  @PostMapping(value="/set")
  @Operation(summary = "redis", description = "redis 데이터 입력")
  public RedisSetRes redisSet(@RequestBody @Validated RedisSetReq params){
    return _redisService.redisSet(params);
  }

  @ResponseBody
  @PostMapping(value="/get")
  @Operation(summary = "redis", description = "redis 데이터 가져오기")
  public RedisGetRes redisGet(@RequestBody @Validated RedisGetReq params){
    return _redisService.redisGet(params);
  }

}
