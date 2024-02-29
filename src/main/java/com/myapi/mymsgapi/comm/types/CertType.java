package com.myapi.mymsgapi.comm.types;

import com.myapi.mymsgapi.comm.utils.StringUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CertType {
  PhoneA("certTypePhoneA"),
  PhoneB("certTypePhoneB"),
  Ipin("certTypeIpin"),
  Card("certTypeCard"),
  Public("certTypePublic"),
  Kakao("certTypeKakao");

  public final String value;

  public static CertType fromValue(final String str) {
    if (StringUtil.isEmpty(str)) {
      return null;
    }

    for (CertType certType : values()) {

      if (str.equals(certType.value)) {
        return certType;
      }
    }

    return null;
  }
}
