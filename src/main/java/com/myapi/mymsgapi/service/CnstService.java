package com.myapi.mymsgapi.service;

import com.myapi.mymsgapi.dao.CommDAO;
import com.myapi.mymsgapi.contoller.comm.dto.CnstReq;
import com.myapi.mymsgapi.contoller.comm.dto.CnstRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CnstService {

  private final CommDAO _commDAO;

  /**
   * 공통 상수 조회
   */
  public CnstRes cnstInf(final CnstReq params) {
    return _commDAO.selectCnstCd(params);
  }

}
