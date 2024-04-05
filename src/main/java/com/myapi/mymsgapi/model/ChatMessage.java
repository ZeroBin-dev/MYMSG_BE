package com.myapi.mymsgapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
  private String messageId; // roomId+"_"+ms 3자리
  private String userId; // 사용자ID
  private String userName; // 사용자명
  private String message; // 메세지
  private String time; // 전송시간
}
