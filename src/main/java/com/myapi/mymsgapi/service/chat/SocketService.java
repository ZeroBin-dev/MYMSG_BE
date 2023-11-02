package com.myapi.mymsgapi.service.chat;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@ServerEndpoint("/socket/chat")
public class SocketService {
  private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

  @OnOpen
  public void opOpen(Session session) {
    log.info("open session : {}, clients={}", session.toString(), clients);
    Map<String, List<String>> res = session.getRequestParameterMap();
    log.info("res={}", res);

    if (!clients.contains(session)) {
      clients.add(session);
      log.info("session open : {}", session);
    } else {
      log.info("이미 연결된 session");
    }
  }

  @OnMessage
  public void onMessage(String message, Session session) throws IOException {
    log.info("receive message : {}", message);

    for (Session s : clients) {
      log.info("send data : {}", message);
      s.getBasicRemote().sendText(message);
    }
  }

  @OnClose
  public void onClose(Session session) {
    log.info("session close : {}", session);
    clients.remove(session);
  }

}
