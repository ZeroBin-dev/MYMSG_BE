package com.myapi.mymsgapi.service.redis;

import com.myapi.mymsgapi.contoller.redis.dto.RedisGetReq;
import com.myapi.mymsgapi.contoller.redis.dto.RedisGetRes;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetReq;
import com.myapi.mymsgapi.contoller.redis.dto.RedisSetRes;
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
  public RedisSetRes redisSet(final RedisSetReq params) {
    RedisSetRes res = new RedisSetRes();
    res.setSuccYn("N");

    ValueOperations<String, String> vop = redisTemplate.opsForValue();
    vop.set(params.getKey(), params.getValue());

    res.setSuccYn("Y");

    return res;
  }

  /**
   * redis get
   */
  public RedisGetRes redisGet(final RedisGetReq params) {
    RedisGetRes res = new RedisGetRes();
    res.setValue("");

    ValueOperations<String, String> vop = redisTemplate.opsForValue();
    String value = vop.get(params.getKey());

    res.setValue(value);
    return res;
  }
}
