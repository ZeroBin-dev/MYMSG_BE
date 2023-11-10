package com.myapi.mymsgapi.service.redis;

import com.myapi.mymsgapi.contoller.redis.dto.RedisGetRequest;
import com.myapi.mymsgapi.contoller.redis.dto.RedisGetResponse;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetRequest;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, String> redisTemplate;

  /**
   * redis set
   */
  public RedisSetResponse redisSet(final RedisSetRequest params) {
    RedisSetResponse res = new RedisSetResponse();
    res.setSuccYn("N");

    ValueOperations<String, String> vop = redisTemplate.opsForValue();
    vop.set(params.getKey(), params.getValue());

    res.setSuccYn("Y");

    return res;
  }

  /**
   * redis get
   */
  public RedisGetResponse redisGet(final RedisGetRequest params) {
    RedisGetResponse res = new RedisGetResponse();
    res.setValue("");

    ValueOperations<String, String> vop = redisTemplate.opsForValue();
    String value = vop.get(params.getKey());

    res.setValue(value);
    return res;
  }
}
