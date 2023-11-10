package com.myapi.mymsgapi.dao.comm;


import com.myapi.mymsgapi.contoller.comm.dto.CnstRequest;
import com.myapi.mymsgapi.contoller.comm.dto.CnstResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommDAO {
  CnstResponse selectCnstCd(CnstRequest paramMap); // 공통상수 조회
}
