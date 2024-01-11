package com.myapi.mymsgapi.service.redis;

import com.google.gson.JsonObject;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.StringUtil;
import com.myapi.mymsgapi.dao.room.RoomDAO;
import com.myapi.mymsgapi.model.UserRoomInfo;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RoomDAO roomDAO;

  private final RedisTemplate<String, String> redisTemplate;

  /**
   * 유저별 채팅방 정보 가져오기
   */
  public List<UserRoomInfo> getUserRoomList(final String userId) {
    // userId 로 현재 참여중인 방번호 찾기
    List<UserRoomInfo> roomInfos = roomDAO.selectRoomList(userId);

    for (UserRoomInfo roomInfo : roomInfos) {
      try {
        JsonObject lastMsg = this.getLast("room:" + roomInfo.getRoomId() + ":messages");
        if (ObjectUtil.isNotEmpty(lastMsg)) {
          roomInfo.setLastMsg(lastMsg.get("message").getAsString());
          roomInfo.setLastMsgDt(lastMsg.get("time").getAsString());
        }
      } catch (Exception e) {
        continue;
      }
    }

    return roomInfos;
  }

  /**
   * redis set
   */
  public void set(final String key, final String value, final boolean isOverride) {
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
  public String getValue(final String key) {
    ValueOperations<String, String> vop = redisTemplate.opsForValue();
    String value = vop.get(key);
    return value;
  }

  /**
   * redis get list
   */
  public List<String> getList(final String key) {
    return redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   * redis get list last data
   */
  public JsonObject getLast(final String key) {
    List<String> values = redisTemplate.opsForList().range(key, -1, -1);
    return (values != null && !values.isEmpty()) ? StringUtil.toJson(values.get(0)) : null;
  }

  /**
   * redis get hash value(JSONObject)
   */
  public JSONObject getJson(final String key, final String group) {
    return (JSONObject) redisTemplate.opsForHash().get(key, group);
  }

  /**
   * redis get hash value(JSONOArray)
   */
  public JSONArray getJsonArray(final String key, final String group) {
    Object obj = redisTemplate.opsForHash().get(key, group);
    return new JSONArray(obj.toString());
  }
}
