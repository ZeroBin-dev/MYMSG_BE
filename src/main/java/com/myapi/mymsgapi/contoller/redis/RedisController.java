package com.myapi.mymsgapi.contoller.redis;

import com.myapi.mymsgapi.contoller.redis.dto.RedisGetRequest;
import com.myapi.mymsgapi.contoller.redis.dto.RedisGetResponse;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetRequest;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetResponse;
import com.myapi.mymsgapi.service.redis.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
  public RedisSetResponse redisSet(@RequestBody @Validated RedisSetRequest params){
    return _redisService.redisSet(params);
  }

  @ResponseBody
  @PostMapping(value="/get")
  @Operation(summary = "redis", description = "redis 데이터 가져오기")
  public RedisGetResponse redisGet(@RequestBody @Validated RedisGetRequest params){
    return _redisService.redisGet(params);
  }

}
