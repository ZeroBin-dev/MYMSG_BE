package com.myapi.mymsgapi.service.chat;

import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.model.ChatRoom;
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

  private Map<String, ChatRoom> chatRooms;

  @PostConstruct
  //의존관게 주입완료되면 실행되는 코드
  private void init() {
    chatRooms = new LinkedHashMap<>();
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
