package com.myapi.mymsgapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MymsgApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(MymsgApiApplication.class, args);
  }

}
