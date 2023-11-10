package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.service.comm.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

  private final FileService _fileService;

  // TODO : 파일 업로드 및 조회 컨트롤러 추가예정
  // 이미지 사이즈 조절
}
