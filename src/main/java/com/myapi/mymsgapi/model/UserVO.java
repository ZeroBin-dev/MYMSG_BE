package com.myapi.mymsgapi.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UserVO implements Serializable {
  @Serial
  private static final long serialVersionUID = 2359662639401792272L;

  private String userId;
  private String userName;

}
