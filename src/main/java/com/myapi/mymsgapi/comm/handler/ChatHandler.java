package com.myapi.mymsgapi.comm.handler;

import com.myapi.mymsgapi.comm.session.SessionKeys;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.SessionUtil;
import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.model.UserRoomInfo;
import com.myapi.mymsgapi.model.vo.UserVO;
import com.myapi.mymsgapi.service.redis.RedisService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
  private static Map<String, List<WebSocketSession>> roomSessionsMap = new HashMap<>();
  private final RedisService _redisService;

  // 연결(클라이언트 접속)
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    // 채팅방 ID 가져오기
    String roomId = getRoomId(session);

    // 채팅방 ID에 해당하는 세션 리스트 가져오기
    List<WebSocketSession> roomSessions = roomSessionsMap.getOrDefault(roomId, new ArrayList<>());

    // 세션 추가
    roomSessions.add(session);

    // 맵에 업데이트
    roomSessionsMap.put(roomId, roomSessions);
    String userId = "";
    // 안읽은갯수만큼 읽음처리
    try {
      HttpSession httpSession = (HttpSession) session.getAttributes().get("httpSession");
      userId = ((UserVO)httpSession.getAttribute(SessionKeys.USER_VO.name())).getLginData().getUserId();
      _redisService.readMessage(roomId, userId);
    }catch (Exception e){

    }

    System.out.println("클라이언트 접속 : [ " + userId + " ] : " + roomId);
  }

  // 연결 종료(클라이언트 접속 해제)
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    // 채팅방 ID 가져오기
    String roomId = getRoomId(session);

    // 채팅방 ID에 해당하는 세션 리스트 가져오기
    List<WebSocketSession> roomSessions = roomSessionsMap.getOrDefault(roomId, new ArrayList<>());

    // 세션 제거
    roomSessions.remove(session);

    // 맵에 업데이트
    roomSessionsMap.put(roomId, roomSessions);

    String userId = "";
    // 안읽음처리
    try{
      HttpSession httpSession = (HttpSession) session.getAttributes().get("httpSession");
      userId = ((UserVO)httpSession.getAttribute(SessionKeys.USER_VO.name())).getLginData().getUserId();
    }catch (Exception e){

    }

    System.out.println("클라이언트 접속 해제 : [ " + userId + " ] : " + roomId);
  }

  // 메시지 수신
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // 채팅방 ID 가져오기
    String roomId = getRoomId(session);

    // 채팅방 ID에 해당하는 세션 리스트 가져오기
    List<WebSocketSession> roomSessions = roomSessionsMap.getOrDefault(roomId, new ArrayList<>());

    // redis 에 정보입력
    _redisService.saveMessage(roomId, message.getPayload());

    ChatMessage chatMessage = ObjectUtil.jsonToObject(message.getPayload(), ChatMessage.class);

    // 전체유저 - 현재접속 유저수 빼기
    try{
      HttpSession httpSession = (HttpSession) session.getAttributes().get("httpSession");
      List<UserRoomInfo> userRoomList = ((UserVO)httpSession.getAttribute(SessionKeys.USER_VO.name())).getRoomList();
      int totalUser = userRoomList.stream().filter(i -> i.getRoomId().equals(roomId))
        .mapToInt(i -> Integer.parseInt(i.getMemberCount())).sum();
      chatMessage.setUnread(String.valueOf(totalUser - roomSessions.size()));
    }catch (Exception e){

    }
    TextMessage textMessage = new TextMessage(ObjectUtil.objectToJsonString(chatMessage));

    // 메시지 전송
    for (WebSocketSession webSocketSession : roomSessions) {
      webSocketSession.sendMessage(textMessage);
    }

  }

  // 오류 처리
  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    exception.printStackTrace();
  }

  // 세션에서 채팅방 ID 가져오기
  private String getRoomId(WebSocketSession session) {
    // WebSocket 연결 URL을 가져옴
    String uri = session.getUri().toString();
    // URI에서 채팅방 ID를 추출하여 반환
    return uri.substring(uri.lastIndexOf("/") + 1);
  }

  // 실시간으로 메세지 전송하기
  public void sendRealtimeMessageInRoom(String roomId, ChatMessage message) throws Exception {
    // 채팅방 ID에 해당하는 세션 리스트 가져오기
    List<WebSocketSession> roomSessions = roomSessionsMap.getOrDefault(roomId, new ArrayList<>());

    for (WebSocketSession webSocketSession : roomSessions) {
      TextMessage textMessage = new TextMessage(ObjectUtil.objectToJsonString(message));
      webSocketSession.sendMessage(textMessage);
    }

  }

}