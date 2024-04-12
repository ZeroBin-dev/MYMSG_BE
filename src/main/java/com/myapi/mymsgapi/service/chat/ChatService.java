package com.myapi.mymsgapi.service.chat;

import com.myapi.mymsgapi.comm.utils.DateUtil;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.StringUtil;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.dao.room.RoomDAO;
import com.myapi.mymsgapi.dao.user.UserDAO;
import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.model.RoomInfo;
import com.myapi.mymsgapi.model.RoomMember;
import com.myapi.mymsgapi.model.UserProfileInfo;
import com.myapi.mymsgapi.model.vo.RoomVO;
import com.myapi.mymsgapi.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

  private final RedisService _redisService;
  private final UserDAO _userDao;
  private final RoomDAO _roomDao;

  @Value("${spring.graphql.websocket.path}")
  private String _wsUrl;

  /**
   * 대화내용 불러오기
   */
  public RoomVO getChatMessages(final String roomId) {
    RoomVO roomVO = new RoomVO();
    // 방기본정보 가져오기
    roomVO.setRoomInfo(_roomDao.selectRoomInfo(roomId));
    // 방유저목록 가져오기
    roomVO.setRoomMembers(_roomDao.selectMemberInfo(roomId));
    // 메시지 목록 가져오기
    roomVO.setMessageList(_redisService.getRoomMessageList(roomId));
    return roomVO;
  }

  /**
   * 프로필 불러오기
   */
  public UserProfileInfo getUserProfile(final String userId) {
    return _userDao.selectUserProfile(userId);
  }

  /**
   * 채팅방 만들기
   */
  public ChatUserJoinRes makeNewChat(ChatUserJoinReq params) {
    String roomId = DateUtil.getDateStr("yyyyMMddHHmmssSSS");
    String userIds = String.join(",", params.getUserIdList());
    ChatUserJoinRes chatUserJoinRes = new ChatUserJoinRes();
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setMessageId("system_" + roomId);
    chatMessage.setTime(DateUtil.getDateStr());
    chatMessage.setMessage(userIds + " 님이 입장하셨습니다.");
    chatMessage.setUserId("system");
    chatMessage.setUserName("system");

    try {
      _redisService.saveMessage(roomId, ObjectUtil.objectToJsonString(chatMessage));
    } catch (Exception e) {

    }

    // room_info 입력 (room_id, room_name)
    _roomDao.insertRoomInfo(new RoomInfo(roomId, StringUtil.nvl(params.getRoomName(), userIds)));
    // room_member 입력 (room_id, member_id)
    for(String userId : params.getUserIdList()){
      _roomDao.insertRoomMember(new RoomMember(roomId, userId));
    }

    chatUserJoinRes.setRoomId(roomId);
    return chatUserJoinRes;
  }

  /**
   * 웹소켓 주소 가져오기
   */
  public String getWsUrl() {
    return this._wsUrl;
  }


}
