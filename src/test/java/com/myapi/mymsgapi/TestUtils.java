package com.myapi.mymsgapi;


import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestUtils {

  @Test
  public static void regxTest() throws Exception{
    System.out.println(beforBaseline("20240318")); // false
  }



  @Test
  public static boolean beforBaseline(String tran_date) throws Exception {
    if(StringUtils.isEmpty(tran_date)) throw new RuntimeException("tran_date 값을 확인하세요.");
    Date pharmDate = new SimpleDateFormat("yyyyMMdd").parse(tran_date);

    Calendar baseline = Calendar.getInstance(); //처리기준일
    baseline.add(Calendar.MONTH , -1);

    return baseline.getTime().before(pharmDate);
  }
}
