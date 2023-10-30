package com.myapi.mymsgapi.dao;


import com.myapi.mymsgapi.contoller.comm.dto.CnstReq;
import com.myapi.mymsgapi.contoller.comm.dto.CnstRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommDAO {
  CnstRes selectCnstCd(CnstReq paramMap); // 공통상수 조회
}
