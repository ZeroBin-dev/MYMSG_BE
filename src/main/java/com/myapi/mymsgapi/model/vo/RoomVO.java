package com.myapi.mymsgapi.model.vo;

import com.myapi.mymsgapi.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RoomVO {
  private RoomInfo roomInfo;
  private List<MemberInfo> roomMembers; // 멤버들
  private List<ChatMessage> messageList;
}
