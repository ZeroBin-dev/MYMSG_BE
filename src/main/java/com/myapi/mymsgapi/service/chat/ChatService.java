package com.myapi.mymsgapi.service.chat;

import com.myapi.mymsgapi.comm.utils.CryptoUtil;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.dao.room.RoomDAO;
import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.model.ChatRoom;
import com.myapi.mymsgapi.model.RoomInfo;
import com.myapi.mymsgapi.model.vo.RoomVO;
import com.myapi.mymsgapi.service.redis.RedisService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

  private final RedisService _redisService;
  private final RoomDAO _rommDao;

  private Map<String, ChatRoom> chatRooms;

  @PostConstruct
  //의존관게 주입완료되면 실행되는 코드
  private void init() {
    chatRooms = new LinkedHashMap<>();
  }

  /**
   * 대화내용 불러오기
   */
  public RoomVO getChatMessages(final String roomId){
    RoomVO roomVO = new RoomVO();
    // 방기본정보 가져오기
    roomVO.setRoomInfo(_rommDao.selectRoomInfo(roomId));
    // 방유저목록 가져오기
    roomVO.setRoomMembers(_rommDao.selectMemberInfo(roomId));
    // 메시지 목록 가져오기
    roomVO.setMessageList(_redisService.getRoomMessageList(roomId));
    return roomVO;
  }


  //채팅방 불러오기
  public List<ChatRoom> findAllRoom() {
    //채팅방 최근 생성 순으로 반환
    List<ChatRoom> result = new ArrayList<>(chatRooms.values());
    Collections.reverse(result);

    return result;
  }

  //채팅방 하나 불러오기
  public ChatRoom findById(String roomId) {
    return chatRooms.get(roomId);
  }

  //채팅방 생성
  public ChatRoom createRoom(String name) {
    ChatRoom chatRoom = ChatRoom.create(name);
    chatRooms.put(chatRoom.getRoomId(), chatRoom);
    return chatRoom;
  }

  /**
   * 채팅방 입장
   */
  public ChatUserJoinRes chatUserJoin(ChatUserJoinReq params) {

    // TODO : redis in 작성 필요
    return null;
  }
}
