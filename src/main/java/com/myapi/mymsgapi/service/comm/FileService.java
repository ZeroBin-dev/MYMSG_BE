package com.myapi.mymsgapi.service.comm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

  //@Value("${spring.file.base-path}")
  private String fileBasePath; // 파일 기본 패스

  public String getFileContents() {

    return "";
  }


}
