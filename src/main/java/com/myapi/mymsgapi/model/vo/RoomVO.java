package com.myapi.mymsgapi.model.vo;

import com.myapi.mymsgapi.model.ChatMessage;
import com.myapi.mymsgapi.model.MemberInfo;
import com.myapi.mymsgapi.model.RoomInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomVO {
  private RoomInfo roomInfo;
  private List<MemberInfo> roomMembers; // 멤버들
  private List<ChatMessage> messageList;
}
