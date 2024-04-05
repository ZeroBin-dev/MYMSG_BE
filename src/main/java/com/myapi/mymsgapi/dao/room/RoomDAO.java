package com.myapi.mymsgapi.dao.room;

import com.myapi.mymsgapi.model.MemberInfo;
import com.myapi.mymsgapi.model.RoomInfo;
import com.myapi.mymsgapi.model.UserRoomInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomDAO {

  /**
   * 방 정보 가져오기
   */
  List<UserRoomInfo> selectRoomList(final String userId);

  RoomInfo selectRoomInfo(final String roomId);

  List<MemberInfo> selectMemberInfo(final String roomId);

}
