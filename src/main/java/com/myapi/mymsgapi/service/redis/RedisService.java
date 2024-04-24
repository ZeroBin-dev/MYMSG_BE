package com.myapi.mymsgapi.service.redis;

import com.google.gson.JsonObject;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.StringUtil;
import com.myapi.mymsgapi.dao.room.RoomDAO;
import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.model.UserRoomInfo;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RoomDAO _roomDAO;

  private final RedisTemplate<String, String> _redisTemplate;

  /**
   * 유저별 채팅방 정보 가져오기
   */
  public List<UserRoomInfo> getUserRoomList(final String userId) {
    // userId 로 현재 참여중인 방번호 찾기
    List<UserRoomInfo> roomInfos = _roomDAO.selectRoomList(userId);

    for (UserRoomInfo roomInfo : roomInfos) {
      try {
        JsonObject lastMsg = this.__getLast("room:" + roomInfo.getRoomId() + ":messages");
        if (ObjectUtil.isNotEmpty(lastMsg)) {
          roomInfo.setLastMsg(lastMsg.get("message").getAsString());

          // 2024-04-05 10:42:41
          String time = lastMsg.get("time").getAsString();
          String msgTime = "";
          if(StringUtil.isNotEmpty(time)){
            msgTime = time.substring(2,4) + "/" + time.substring(5,7) + "/" + time.substring(8, 10) + " " +
                            time.substring(11,13)+":"+time.substring(14,16);
          }

          roomInfo.setLastMsgDt(msgTime);
        }
      } catch (Exception e) {
      }
    }

    return roomInfos;
  }

  /**
   * 방번호별 대화내용 가져오기
   */
  public List<ChatMessage> getRoomMessageList(final String roomId) {
    ArrayList<ChatMessage> chatMessages = new ArrayList<>();

    List<String> messages = __getList("room:" + roomId + ":messages");
    for (String message : messages) {
      chatMessages.add(ObjectUtil.jsonToObject(message, ChatMessage.class));
    }

    return chatMessages;
  }

  /**
   * 대화내용 저장하기
   * 새로운 대화방 만들기
   */
  public boolean saveMessage(String roomId, String jsonString) {
    try {
      this.__appendToList(roomId, jsonString);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /*******************************************************************************************************************/
  /*******************************************************************************************************************/

  /**
   * redis set
   */
  private void __set(final String key, final String value, final boolean isOverride) {
    ValueOperations<String, String> vop = _redisTemplate.opsForValue();

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
   * redis list append
   */
  private void __appendToList(final String roomId, final String message) {
    String listKey = "room:" + roomId + ":messages";
    ListOperations<String, String> lop = _redisTemplate.opsForList();
    lop.rightPush(listKey, message);
  }

  /**
   * redis get value
   */
  private String __getValue(final String key) {
    ValueOperations<String, String> vop = _redisTemplate.opsForValue();
    String value = vop.get(key);
    return value;
  }

  /**
   * redis get list
   */
  private List<String> __getList(final String key) {
    return _redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   * redis get list last data
   */
  private JsonObject __getLast(final String key) {
    List<String> values = _redisTemplate.opsForList().range(key, -1, -1);
    return (values != null && !values.isEmpty()) ? ObjectUtil.toJson(values.get(0)) : null;
  }

  /**
   * redis get hash value(JSONObject)
   */
  private JSONObject __getJson(final String key, final String group) {
    return (JSONObject) _redisTemplate.opsForHash().get(key, group);
  }

  /**
   * redis get hash value(JSONOArray)
   */
  private JSONArray __getJsonArray(final String key, final String group) {
    Object obj = _redisTemplate.opsForHash().get(key, group);
    return new JSONArray(obj.toString());
  }
}
