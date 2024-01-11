package com.myapi.mymsgapi;


import org.junit.jupiter.api.Test;

public class TestUtils {

  @Test
  public void regxTest(){
    String pattern = "[a-zA-Z]+-\\d+";

    String input1 = "메뉴-2";
    String input2 = "123-456";

    System.out.println(input1.matches(pattern)); // true
    System.out.println(input2.matches(pattern)); // false
  }
}
