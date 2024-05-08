package com.myapi.mymsgapi.dao.room;

import com.myapi.mymsgapi.contoller.chat.dto.ChatUserInfoReq;
import com.myapi.mymsgapi.contoller.chat.dto.RoomNameChangeReq;
import com.myapi.mymsgapi.model.MemberInfo;
import com.myapi.mymsgapi.model.RoomInfo;
import com.myapi.mymsgapi.model.RoomMember;
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

  int insertRoomInfo(final RoomInfo roomInfo);

  int insertRoomMember(final RoomMember roomMember);

  String selectExistRoomId(final String ids);

  /**
   * 채팅방 나가기
   */
  int exitRoom(final ChatUserInfoReq params);

  /**
   * 방에 사람 있나 체크
   */
  int checkExistMember(final ChatUserInfoReq params);

  /**
   * 방 아예 삭제
   */
  int deleteRoomInfo(final ChatUserInfoReq params);

  /**
   * 방정보 가져오기
   */
  RoomInfo selectRoomProfileInfo(final String roomId);

  /**
   * 채팅방 이름 변경
   */
  int updateRoomName(RoomNameChangeReq params);

  /**
   * 유저 초대하기
   */
  void inviteRoom(ChatUserInfoReq params);
}
