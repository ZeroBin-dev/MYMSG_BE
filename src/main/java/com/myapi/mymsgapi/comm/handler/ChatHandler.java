package com.myapi.mymsgapi.comm.handler;

import com.google.gson.JsonObject;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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

    System.out.println("[{}] 클라이언트 접속" + session);
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

    System.out.println("[{}] 클라이언트 접속 해제" + session);
  }

  // 메시지 수신
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // 채팅방 ID 가져오기
    String roomId = getRoomId(session);

    // 채팅방 ID에 해당하는 세션 리스트 가져오기
    List<WebSocketSession> roomSessions = roomSessionsMap.getOrDefault(roomId, new ArrayList<>());

    // 메시지 전송
    for (WebSocketSession webSocketSession : roomSessions) {
      webSocketSession.sendMessage(message);
    }

    // redis 에 정보입력
    _redisService.saveMessage(roomId, message.getPayload());
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
}