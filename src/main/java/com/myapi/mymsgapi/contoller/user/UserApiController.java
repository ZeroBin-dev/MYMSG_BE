package com.myapi.mymsgapi.contoller.user;

import com.myapi.mymsgapi.contoller.user.dto.*;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
import com.myapi.mymsgapi.model.vo.UserVO;
import com.myapi.mymsgapi.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserApiController {
  private final UserService _userService;

  @ResponseBody
  @PostMapping(value = "/user/loginProc")
  @Operation(summary = "로그인", description = "로그인 처리")
  public UserVO userLoginProc(@RequestBody @Validated UserLginReq params) {
    return _userService.userLoginProc(params);
  }

  @ResponseBody
  @PostMapping(value = "/user/jnProc")
  @Operation(summary = "회원가입", description = "회원가입 처리")
  public BaseUpdateResponse userJnProc(@RequestBody @Validated UserRegsReq params) {
    return _userService.userJnProc(params);
  }

  /**
   * 친구추가하기
   */
  @ResponseBody
  @PostMapping(value = "/addFriend")
  @Operation(summary = "친구추가", description = "친구추가하기")
  public BaseUpdateResponse addFriend(@RequestBody @Validated AddFriendReq params) {
    return _userService.addFriend(params);
  }

  /**
   * 친구찾기
   */
  @ResponseBody
  @PostMapping(value = "/findFriend")
  @Operation(summary = "친구찾기", description = "친구찾기")
  public FindFriendRes findFriend(@RequestBody @Validated FindFriendReq params) {
    return _userService.findFriend(params);
  }

  /**
   * 즐겨찾기 업데이트
   */
  @ResponseBody
  @PostMapping(value = "/updateBookmark")
  @Operation(summary = "즐겨찾기 변경", description = "즐겨찾기 변경")
  public BaseUpdateResponse updateBookmark(@RequestBody @Validated UpdateBookmarkReq params) {
    return _userService.updateBookmark(params);
  }

  /**
   * 상태메세지 업데이트
   */
  @ResponseBody
  @PostMapping(value = "/updateStatMsg")
  @Operation(summary = "상태메세지 변경", description = "상태메세지 변경")
  public BaseUpdateResponse updateStatMsg(@RequestBody @Validated UpdateStatMsgReq params) {
    return _userService.updateStatMsg(params);
  }

  @ResponseBody
  @PostMapping(value = "/updateProfileImage")
  @Operation(summary = "프로필사진 변경", description = "프로필사진 변경")
  public BaseUpdateResponse updateProfileImage(@RequestParam MultipartFile file) {
    return _userService.updateProfileImage(file);
  }




}
