package com.myapi.mymsgapi.service.chat;

import com.myapi.mymsgapi.comm.handler.ChatHandler;
import com.myapi.mymsgapi.comm.utils.DateUtil;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.StringUtil;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserInfoReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.contoller.chat.dto.RoomNameChangeReq;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
import com.myapi.mymsgapi.contoller.user.dto.FindFriendReq;
import com.myapi.mymsgapi.contoller.user.dto.FindFriendRes;
import com.myapi.mymsgapi.dao.room.RoomDAO;
import com.myapi.mymsgapi.dao.user.UserDAO;
import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.model.RoomInfo;
import com.myapi.mymsgapi.model.RoomMember;
import com.myapi.mymsgapi.model.UserProfileInfo;
import com.myapi.mymsgapi.model.vo.RoomVO;
import com.myapi.mymsgapi.service.redis.RedisService;
import com.myapi.mymsgapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

  private final RedisService _redisService;
  private final UserService _userService;
  private final ChatHandler _chatHandler;
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
  @Transactional
  public ChatUserJoinRes makeNewChat(ChatUserJoinReq params) {
    String roomId = DateUtil.getDateStr("yyyyMMddHHmmssSSS");
    Collections.sort(params.getUserIdList());
    String userIds = String.join(",", params.getUserIdList());
    String existRoomId = _roomDao.selectExistRoomId(userIds);
    if (StringUtil.isNotEmpty(existRoomId)) {
      roomId = existRoomId;
    } else {
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
      RoomInfo roomInfo = new RoomInfo();
      roomInfo.setRoomId(roomId);
      roomInfo.setRoomName(StringUtil.nvl(params.getRoomName(), userIds));
      _roomDao.insertRoomInfo(roomInfo);

      // room_member 입력 (room_id, member_id)
      for (String userId : params.getUserIdList()) {
        _roomDao.insertRoomMember(new RoomMember(roomId, userId));
      }
    }


    return new ChatUserJoinRes(roomId);
  }

  /**
   * 웹소켓 주소 가져오기
   */
  public String getWsUrl() {
    return this._wsUrl;
  }


  /**
   * 채팅방 나가기
   */
  @Transactional
  public BaseUpdateResponse exitRoom(ChatUserInfoReq params) {
    // 방 나가기
    _roomDao.exitRoom(params);

    // 방에 나 혼자 있으면 방자체를 삭제
    int count = _roomDao.checkExistMember(params);
    if (count == 0) {
      // 방삭제
      _roomDao.deleteRoomInfo(params);

      // redis 대화내용 삭제
      _redisService.exitRoom(params.getRoomId());

    } else {
      // 나갔다는 메세지 뿌려주기
      ChatMessage chatMessage = new ChatMessage();
      chatMessage.setMessageId("system_" + params.getRoomId());
      chatMessage.setTime(DateUtil.getDateStr());
      chatMessage.setMessage(params.getUserId() + " 님이 퇴장하셨습니다.");
      chatMessage.setUserId("system");
      chatMessage.setUserName("system");

      try {
        _chatHandler.sendRealtimeMessageInRoom(params.getRoomId(), chatMessage);
        _redisService.saveMessage(params.getRoomId(), ObjectUtil.objectToJsonString(chatMessage));
        _redisService.exitRoomUser(params.getRoomId(), params.getUserId());
      } catch (Exception e) {

      }
    }

    return new BaseUpdateResponse("Y", "방을 나왔습니다.");
  }

  /**
   * 채팅방 프로필 정보 조회
   */
  public RoomInfo getRoomProfileInfo(String roomId) {
    return _roomDao.selectRoomProfileInfo(roomId);
  }

  /**
   * 채팅방 이름 변경
   */
  @Transactional
  public BaseUpdateResponse updateRoomName(RoomNameChangeReq params) {
    BaseUpdateResponse baseUpdateResponse = new BaseUpdateResponse();

    _roomDao.updateRoomName(params);

    baseUpdateResponse.setSuccYn("Y");
    baseUpdateResponse.setMsg("채팅방 이름변경 완료하였습니다.");

    return baseUpdateResponse;
  }

  /**
   * 유저 초대하기
   */
  @Transactional
  public BaseUpdateResponse inviteRoom(ChatUserInfoReq params) {
    BaseUpdateResponse baseUpdateResponse = new BaseUpdateResponse();

    FindFriendReq findFriendReq = new FindFriendReq();
    findFriendReq.setUserId(params.getUserId());
    FindFriendRes findFriendRes = _userService.findFriend(findFriendReq);

    if(findFriendRes.getExistYn().equals("Y")){
      RoomMember roomMember = new RoomMember();
      roomMember.setRoomId(params.getRoomId());
      roomMember.setMemberId(params.getUserId());
      _roomDao.insertRoomMember(roomMember);

      ChatMessage chatMessage = new ChatMessage();
      chatMessage.setMessageId("system_" + params.getRoomId());
      chatMessage.setTime(DateUtil.getDateStr());
      chatMessage.setMessage(params.getUserId() + " 님이 입장하셨습니다.");
      chatMessage.setUserId("system");
      chatMessage.setUserName("system");

      try {
        _chatHandler.sendRealtimeMessageInRoom(params.getRoomId(), chatMessage);
        _redisService.saveMessage(params.getRoomId(), ObjectUtil.objectToJsonString(chatMessage));
      } catch (Exception e) {
        e.printStackTrace();
      }

      baseUpdateResponse.setSuccYn("Y");
      baseUpdateResponse.setMsg("초대 완료하였습니다");

    } else {
      baseUpdateResponse.setSuccYn("N");
      baseUpdateResponse.setMsg("유저가 존재하지 않습니다.");
    }

    return baseUpdateResponse;
  }
}
