package com.myapi.mymsgapi.model.vo;

import com.myapi.mymsgapi.model.UserFriendInfo;
import com.myapi.mymsgapi.model.UserLginInfo;
import com.myapi.mymsgapi.model.UserRoomInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO implements Serializable {
  @Serial
  private static final long serialVersionUID = 2359662639401792272L;

  private String lginYn;
  private UserLginInfo lginData;
  private List<UserRoomInfo> roomList;
  private List<UserFriendInfo> friendList;
}
