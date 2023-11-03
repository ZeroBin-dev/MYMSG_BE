package com.myapi.mymsgapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MymsgApiApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(MymsgApiApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
