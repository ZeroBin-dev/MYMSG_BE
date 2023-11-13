package com.myapi.mymsgapi.service.redis;

import com.myapi.mymsgapi.comm.utils.StringUtil;
import com.myapi.mymsgapi.model.UserRoomInfo;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, String> redisTemplate;

  /**
   * 유저별 채팅방 정보 생성
   */
  public String setUserRoom(final String userId) {
    redisSet(userId, "test", true);
    return "";
  }

  /**
   * 유저별 채팅방 정보 가져오기
   */
  public List<UserRoomInfo> getUserRoomList(final String userId) {
    List<UserRoomInfo> userRoomList = new ArrayList<>();

    JSONArray roomResult = redisGetJsonArray(userId, "roomInfo");

    for (int i = 0; i < roomResult.length(); i++) {
      JSONObject jo = roomResult.getJSONObject(i);

      UserRoomInfo userRoomInfo = new UserRoomInfo();
      userRoomInfo.setRoomId(jo.get("roomId").toString());
      userRoomInfo.setLastMsg(jo.get("lastMsg").toString());
      userRoomInfo.setLastMsgDt(jo.get("lastMsgDt").toString());
      userRoomInfo.setMemberCount(jo.get("memberCount").toString());

      userRoomList.add(userRoomInfo);
    }

    return userRoomList;
  }

  /**
   * redis set
   */
  public void redisSet(final String key, final String value, final boolean isOverride) {
    ValueOperations<String, String> vop = redisTemplate.opsForValue();

    if (isOverride) {
      vop.set(key, value);
    } else {
      String result = vop.get(key);
      if (result != null && StringUtil.isNotEmpty(result)) {
        // TODO : append ?
      } else {
        vop.set(key, value);
      }
    }
  }

  /**
   * redis get value
   */
  public String redisGetValue(final String key) {
    ValueOperations<String, String> vop = redisTemplate.opsForValue();
    String value = vop.get(key);
    return value;
  }

  /**
   * redis get hash value(JSONObject)
   */
  public JSONObject redisGetJsonObject(final String key, final String group) {
    return (JSONObject) redisTemplate.opsForHash().get(key, group);
  }

  /**
   * redis get hash value(JSONOArray)
   */
  public JSONArray redisGetJsonArray(final String key, final String group) {
    Object obj = redisTemplate.opsForHash().get(key, group);
    return new JSONArray(obj.toString());
  }
}
