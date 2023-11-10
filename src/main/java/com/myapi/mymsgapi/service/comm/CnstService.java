package com.myapi.mymsgapi.service.comm;

import com.myapi.mymsgapi.dao.comm.CommDAO;
import com.myapi.mymsgapi.contoller.comm.dto.CnstRequest;
import com.myapi.mymsgapi.contoller.comm.dto.CnstResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CnstService {

  private final CommDAO _commDAO;

  /**
   * 공통 상수 조회
   */
  public CnstResponse cnstInf(final CnstRequest params) {
    return _commDAO.selectCnstCd(params);
  }

}
